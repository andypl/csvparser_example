package pl.info.czerniak.csvparser.dao;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.postgresql.copy.CopyIn;
import org.postgresql.copy.CopyManager;
import org.postgresql.jdbc.PgConnection;
import pl.info.czerniak.csvparser.database.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class PostgresTestDAOImpl implements TestDAO {

    private static final String POSTGRES_COPY_COMMAND = "COPY test FROM STDIN WITH DELIMITER ','";
    private CopyIn copyIn;
    private PgConnection pgConnection;
    private Connection connection;

    public PostgresTestDAOImpl() throws SQLException {
        org.apache.log4j.BasicConfigurator.configure();
        this.connection = ConnectionManager.getInstance().getConnection();
        this.pgConnection = connection.unwrap(PgConnection.class);
        CopyManager copyManager = new CopyManager(pgConnection);
        this.copyIn = copyManager.copyIn(POSTGRES_COPY_COMMAND);
    }

    @Override
    public void create(CSVParser csvParser) throws SQLException {
        try {
            for (CSVRecord record : csvParser) {
                if(rowFilter(record)){
                    copyIn.writeToCopy(exampleTransformationFunction(record), 0, exampleTransformationFunction(record).length);
                }
            }
            copyIn.endCopy();
        } finally {
            if(copyIn.isActive()){copyIn.cancelCopy();}
        }
    }

    private boolean rowFilter(CSVRecord record){ return record.getRecordNumber() % 2 == 0;}

    private byte[] exampleTransformationFunction(CSVRecord record){
        return new String(record.get(1) + "," + record.get(3)
                + "," + UUID.randomUUID().toString()+"\n").getBytes(); //Add \n for record end marker.
    }
}