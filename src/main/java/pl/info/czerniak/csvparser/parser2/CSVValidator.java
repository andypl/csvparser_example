package pl.info.czerniak.csvparser.parser2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import pl.info.czerniak.csvparser.exception.EmptyFileException;
import pl.info.czerniak.csvparser.validators.FileValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class CSVValidator {

    private long colsNumber;
    private boolean header;
    private CSVFormat csvFormat;
    private Character delimiter;
    private List<String> headerNames;
    private String fileName;
    private File file;
    private CSVParser csvParser;

    public CSVValidator(long colsNumber, boolean header, CSVFormat csvFormat, String fileName) {
        this.colsNumber = colsNumber;
        this.header = header;
        this.csvFormat = csvFormat;
        this.fileName = fileName;
        init();
    }

    public void init(){
        try {
            this.file = FileValidator.validateInputFile(this.fileName);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage().toString());
        } catch (EmptyFileException e) {
            System.out.println(e.getMessage().toString());
        }
        System.err.println("Input file initialized!");

        try {
            this.csvParser = CSVParser.parse(this.file, Charset.defaultCharset(), this.csvFormat);
        } catch (IOException e) {
            System.out.println(e.getMessage().toString());
        }
        System.err.println("Parser initialized!");

    }

    private boolean validateDelimiter(){
        return false;
    }

    private boolean isHeader(){
        return false;
    }

    private boolean validateHeaaderNames(){
        return false;
    }

    private boolean validateColumnNumbers(){
        return false;
    }

    private void parseCSV(String fileName){

    }
}