package pl.info.czerniak.csvparser.parser;

import org.apache.commons.csv.CSVRecord;

/**
 * An interface for converter classes.
 */
public interface Converter {
    void convert();
    boolean isRowConvertable(CSVRecord record);
    byte[] getResult();
}
