package pl.info.czerniak.csvparser.parser;

import org.apache.commons.csv.CSVParser;
import pl.info.czerniak.csvparser.exception.EmptyFileException;
import pl.info.czerniak.csvparser.validators.FileValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is used for parse input CSV file.
 */
public class CSVFileParserImpl implements CSVFileParser{

    File file;
    private CSVParser csvParser;
    private CSVFileSpec csvFileSpec;
    private boolean isHeaderValidated = false;
    private boolean isCSVDataValidated = false;
    private long recordsCount = 0;

    /**
     * This method will return CSVParser object that link to a csvfile.
     */
    public CSVParser getCsvParser() {
        try {
            this.csvParser = CSVParser.parse(this.file, csvFileSpec.getCharset(), csvFileSpec.getCsvFormat().withHeader());
        } catch (IOException e) { System.out.println(e.getMessage() + e.getCause()); }
        return csvParser;
    }

    public CSVFileParserImpl(String fileName, CSVFileSpec csvFileSpec){
        this.csvFileSpec = csvFileSpec;
        this.file = readFileForParse(fileName);
    }

    private File readFileForParse(String filName) {
        File file = null;
        try{
            file = FileValidator.validateInputFile(filName);
        }catch (FileNotFoundException | EmptyFileException e){
            System.out.println(e.getMessage());
        }
        return file;
    }

    //TO DO: Add validation of input file here
    @Override
    public void parse() throws IOException {
            //csvParser = CSVParser.parse(this.file, csvFileSpec.getCharset(), csvFileSpec.getCsvFormat().withHeader());

    }
}
