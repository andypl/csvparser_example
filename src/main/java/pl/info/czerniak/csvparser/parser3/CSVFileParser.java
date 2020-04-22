package pl.info.czerniak.csvparser.parser3;

import org.apache.commons.csv.CSVParser;

import java.io.File;

public interface CSVFileParser {
    File readFileForParse(String filName);
    boolean validateFile(File file, CSVFileSpec csvFileSpec);
    CSVParser parse(File file, CSVFileSpec csvFileSpec);
}
