package pl.info.czerniak.csvparser.parser2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import pl.info.czerniak.csvparser.exception.CSVHeaderMismatchException;
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
    private List<String> colsHeaderNames;
    private String fileName;
    private File file;
    private CSVParser csvParser;

    public CSVValidator(long colsNumber, boolean header, CSVFormat csvFormat, String fileName, List<String> headerList) throws CSVHeaderMismatchException {
        this.colsNumber = colsNumber;
        this.header = header;
        this.csvFormat = csvFormat;
        this.fileName = fileName;
        this.colsHeaderNames = headerList;
        init();
    }

    public void init() throws CSVHeaderMismatchException {
        initializeFile();
        initializeParser();
        validateHeader();
    }

    private void validateHeader() throws CSVHeaderMismatchException {
        if(this.header != csvParser.getHeaderNames().isEmpty()) {
            throw new CSVHeaderMismatchException("File header: " + csvParser.getHeaderNames().toString() + "Typed: " + this.colsHeaderNames.toString());
        }
    }

    private boolean validateColumnNumbers(){
        return false;
    }

    private void initializeFile(){
        try {
            this.file = FileValidator.validateInputFile(this.fileName);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (EmptyFileException e) {
            System.out.println(e.getMessage());
        }
        System.err.println("Input file initialized!");
    }

    private void initializeParser(){
        try {
            this.csvParser = CSVParser.parse(this.file, Charset.defaultCharset(), this.csvFormat);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.err.println("Parser initialized!");
    }
}