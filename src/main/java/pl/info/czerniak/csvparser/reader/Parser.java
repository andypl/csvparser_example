package pl.info.czerniak.csvparser.reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * This class is used for parse input CSV file.
 */
public class Parser {

    private File file;
    private CSVParser csvParser;
    private CSVSpec csvSpec;
    private boolean isHeaderValidated = false;
    private boolean isCSVDataValidated = false;
    private long recordsCount = 0;

    /**
     * This method will return CSVParser object that link to a csvfile.
     */

    Parser(){};

    CSVParser getDefaultParserForFile(String fileName) throws IOException {
        this.file = openFile(fileName);
        this.csvParser = CSVParser.parse(this.file, Charset.defaultCharset(), CSVFormat.DEFAULT);
        return this.csvParser;
    }

    /**
     *
     * @param fileName File for witch parser will be returned
     * @param csvSpec Specification for input CSV file for validation
     * @return Return CSVParser for given file.
     * @throws IOException
     */
    CSVParser getParserForFile(String fileName, CSVSpec csvSpec) throws IOException {
        return null;
    }

    private File openFile(String fileName) throws FileNotFoundException, EmptyFileException {
        File file = new File(fileName);
        if(!file.exists()){
            throw new FileNotFoundException("File with that name not exist!");
        }else if(file.length() == 0){
            throw new EmptyFileException("File is empty!");
        }
        return file;
    }
}
