package pl.info.czerniak.csvparser.parser3;

import org.apache.commons.csv.CSVParser;

import java.io.IOException;

/**
 * Interface for Parser classes.
 */
public interface CSVFileParser {
     void parse() throws IOException;
}
