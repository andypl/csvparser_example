package pl.info.czerniak.csvparser.printer;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.postgresql.copy.CopyIn;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class for printing into PostgreSQL Database using COPY API.
 * It get as an argument:
 * csvParser that is linked to a csv file,
 * Connection that represent a connection to a database,
 * Converter interface for conversion purpose.
 */
public class Printer {

    private DatabaseManager databaseManager;
    private CopyIn copyIn;
    private Converter converter;
    private CSVParser csvParser;

    /**
     *
     * @param csvParser Object that is linked into a CSV file.
     * @param connection Connection to the PostgreSQL Database.
     * @param converter Interface for converter.
     * @throws SQLException
     */
    Printer(CSVParser csvParser, Connection connection, Converter converter) throws SQLException {
        this.csvParser = csvParser;
        this.databaseManager = new DatabaseManager(connection);
        this.copyIn = databaseManager.getCopyIn();
        this.converter = converter;
    }

    long print() throws SQLException {
        long rowConverted = 0;
            for(CSVRecord record : csvParser){
                if(converter.isRowConvertable(record)){
                    converter.convert();
                    this.copyIn.writeToCopy(converter.getResult(),0,converter.getResult().length);
                }
            }
        rowConverted = copyIn.endCopy();
        return rowConverted;
    }
}
