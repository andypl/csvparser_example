package pl.info.czerniak.csvparser.parser2;

import org.apache.commons.csv.CSVParser;
import pl.info.czerniak.csvparser.exception.EmptyFileException;

import java.io.File;
import java.io.FileNotFoundException;

public interface FileParser {
    File read(String fileName) throws FileNotFoundException, EmptyFileException;
    CSVParser parse(File file);
    boolean validate(CSVParser csvParser);
}