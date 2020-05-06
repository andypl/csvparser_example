package pl.info.czerniak.csvparser.reader;

import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;

public class ReaderService {
    private File inputFile = null;
    private boolean fileValid = false;
    private CSVParser csvParser;

    /**
     * Wrapper for parser class.
     */
    public ReaderService(){}

    //TODO Implement parsing with csvspec.
    public CSVParser getParserForFile(String fileName, CSVSpec csvSpec){
        return null;
    }

    public CSVParser getDefaultParserForFile(String fileName){
        Parser parser = new Parser();
        try {
            this.csvParser = parser.getDefaultParserForFile(fileName);
        } catch (IOException e) {
            e.getMessage();
        }
        return this.csvParser;
    }

}