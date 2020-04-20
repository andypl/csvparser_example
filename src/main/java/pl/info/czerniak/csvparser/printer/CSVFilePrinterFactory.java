package pl.info.czerniak.csvparser.printer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;

public interface CSVFilePrinterFactory {
    void printToFile(CSVParser csvParser, CSVFormat csvFormat, String fileName) throws IOException;
}
