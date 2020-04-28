package pl.info.czerniak.csvparser.converter;

import org.apache.commons.csv.CSVRecord;

/**
 * An interface for converter classes.
 */
public interface Converter2 {
    void convert();
    boolean isRowConvertable(CSVRecord record);
    byte[] getResult();
}
