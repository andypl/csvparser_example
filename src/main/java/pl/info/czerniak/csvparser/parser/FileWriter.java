package pl.info.czerniak.csvparser.parser;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.postgresql.copy.CopyIn;

import java.sql.Connection;
import java.sql.SQLException;

class FileWriter{
    private CSVParser csvParser;
    private CopyAPIManager copyAPIManager;
    private CopyIn copyIn;
    private Converter converter;

    FileWriter(CSVParser csvParser, Connection connection, String sql, Converter converter){
        this.csvParser = csvParser;
        try {
            this.copyAPIManager = new CopyAPIManager(connection, sql);
        } catch (SQLException e) {
            System.err.println("Cannot create a connection using sql stantment: " + sql);
        }
        this.copyIn = copyAPIManager.getCopyAPIForConnection();
        this.converter = converter;
    }

    long write() throws SQLWriteException, SQLEndCopyException {
        long rowPrinted;
        for(CSVRecord record : csvParser){
            if(converter.isRowConvertable(record)){
                converter.convert();
                try {
                    this.copyIn.writeToCopy(converter.getResult(),0,converter.getResult().length);
                } catch (SQLException e) {
                    throw new SQLWriteException(record.getRecordNumber());
                }
            }
        }
        try {
            rowPrinted = copyIn.endCopy();
        } catch (SQLException e) {
            throw new SQLEndCopyException();
        }
        return rowPrinted;
    }
}
