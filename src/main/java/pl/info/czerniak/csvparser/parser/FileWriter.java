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

    FileWriter(CSVParser csvParser, Connection connection, String sql, Converter converter) throws SQLException{
        this.csvParser = csvParser;
        this.copyAPIManager = new CopyAPIManager(connection, sql);
        this.copyIn = copyAPIManager.getCopyAPIForConnection();
        this.converter = converter;
    }

    long write() throws SQLException {
        long rowPrinted;
        for(CSVRecord record : csvParser){
            if(converter.isRowConvertable(record)){
                converter.convert();
                try {
                    this.copyIn.writeToCopy(converter.getResult(),0,converter.getResult().length);
                } catch (SQLException e) {
                    throw new SQLException("Failed write " + record.getRecordNumber() + " record to database");
                }
            }
        }
        try {
            rowPrinted = copyIn.endCopy();
        } catch (SQLException e) {
            throw new SQLException("Problem with ending writing operation!");
        }
        return rowPrinted;
    }
}
