package pl.info.czerniak.csvparser.converter;

import org.apache.commons.csv.CSVRecord;

import java.sql.SQLException;

/**
 * An interface for converter classes.
 */
public interface Converter {
    void convert() throws SQLException;
    byte[] columnFilter(CSVRecord record);
    boolean rowFilter(CSVRecord record);
}
