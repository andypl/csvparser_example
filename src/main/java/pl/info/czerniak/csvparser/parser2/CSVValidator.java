package pl.info.czerniak.csvparser.parser2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import pl.info.czerniak.csvparser.exception.CSVHeaderMismatchException;
import pl.info.czerniak.csvparser.exception.EmptyFileException;
import pl.info.czerniak.csvparser.exception.IncorrectColNumberFileException;
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

    public CSVValidator(CSVFormat csvFormat, String fileName, List<String> headerList) throws CSVHeaderMismatchException, IncorrectColNumberFileException {
        this.csvFormat = csvFormat;
        this.fileName = fileName;
        this.colsHeaderNames = headerList;
        init();
    }

    public void init() throws CSVHeaderMismatchException, IncorrectColNumberFileException {
        initializeFile();
        initializeParser();
        validateHeader();
        validateCSVRecords();
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

    private void validateHeader() throws CSVHeaderMismatchException {
        if(!this.colsHeaderNames.equals(csvParser.getHeaderNames())){
            throw new CSVHeaderMismatchException("CSVHeader: " + csvParser.getHeaderNames() + "Header: " + this.colsHeaderNames);
        }
    }

    private void validateCSVRecords() throws IncorrectColNumberFileException {
        int recordSum = 0;
        for(CSVRecord record : csvParser){
            if(!record.isConsistent()){
                throw new IncorrectColNumberFileException("Record number: " + record.getRecordNumber());
            }
            recordSum++;
        }
        System.err.println("Records validation compleated. Validated: " + recordSum + "records.");
    }
}
