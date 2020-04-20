package pl.info.czerniak.csvparser.parser;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import pl.info.czerniak.csvparser.exception.EmptyFileException;
import pl.info.czerniak.csvparser.exception.IncorrectColNumberFileException;
import pl.info.czerniak.csvparser.validators.FileValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class CSVFileParserFactoryImpl implements CSVFileParserFactory{

    private File file = null;
    private CSVParser csvParser = null;

    @Override
    public boolean validateCSVFile(CSVParser csvParser, CSVValidator validator){
        if(csvParser.getHeaderNames().isEmpty() && !validator.isHeader()){
            int sum = 0;
            for(CSVRecord record : csvParser){
                if(validator.getColNumbers() != countColumns(record)){
                    return false;
                }
            }
            return true;
        }else if(!csvParser.getHeaderNames().isEmpty() && !validator.isHeader()){
            for(CSVRecord record : csvParser){
                if(countColumns(record) != validator.getColNumbers()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public CSVParser getParserForFileName(String fileName, CSVValidator validator) throws IncorrectColNumberFileException {
        try{
            this.file = FileValidator.validateInputFile(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EmptyFileException e) {
            e.printStackTrace();
        }

        try {
            this.csvParser = CSVParser.parse(this.file, validator.getCharset(), validator.getCsvFormat());
        } catch (IOException e) { e.printStackTrace();}

        if(validateCSVFile(this.csvParser, validator)){
            try {
                this.csvParser = CSVParser.parse(this.file, validator.getCharset(), validator.getCsvFormat());
            } catch (IOException e) { e.printStackTrace();}
            return this.csvParser;
        }else{
            throw new IncorrectColNumberFileException("");
        }
    }

    private int countColumns(CSVRecord record){
        int sum = 0 ;
        Iterator<String> iterator = record.iterator();
        while(iterator.hasNext()){
            sum++;
            iterator.next();
        }
        return sum;
    }
}
