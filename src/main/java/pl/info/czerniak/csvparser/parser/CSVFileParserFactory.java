package pl.info.czerniak.csvparser.parser;

import org.apache.commons.csv.CSVParser;
import pl.info.czerniak.csvparser.exception.IncorrectColNumberFileException;

public interface CSVFileParserFactory {
    boolean validateCSVFile(CSVParser csvParser, CSVValidator validator) throws IncorrectColNumberFileException;
    CSVParser getParserForFileName(String fileName, CSVValidator validator) throws IncorrectColNumberFileException;
}
