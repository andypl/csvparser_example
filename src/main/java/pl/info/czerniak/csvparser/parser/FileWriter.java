package pl.info.czerniak.csvparser.parser;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.postgresql.copy.CopyIn;

import java.sql.Connection;
import java.sql.SQLException;

class FileWriter {
    private CSVParser csvParser;
    private DatabaseManager databaseManager;
    private CopyIn copyIn;
    private Converter converter;

    FileWriter(CSVParser csvParser, Connection connection, Converter converter) throws SQLException {
        this.csvParser = csvParser;
        this.databaseManager = new DatabaseManager(connection);
        this.copyIn = databaseManager.getCopyIn();
        this.converter = converter;
    }

    long write() throws SQLException {
        long rowPrinted;
        for(CSVRecord record : csvParser){
            if(converter.isRowConvertable(record)){
                converter.convert();
                this.copyIn.writeToCopy(converter.getResult(),0,converter.getResult().length);
            }
        }
        rowPrinted = copyIn.endCopy();
        return rowPrinted;
    }
}
