package pl.info.czerniak.csvparser.parser2;

import org.apache.commons.csv.CSVParser;
import pl.info.czerniak.csvparser.exception.EmptyFileException;
import pl.info.czerniak.csvparser.validators.FileValidator;

import java.io.File;
import java.io.FileNotFoundException;

public class CSVFileParser implements FileParser{

    boolean isValidated = false;

    @Override
    public File read(String fileName) throws FileNotFoundException, EmptyFileException {
        return FileValidator.validateInputFile(fileName);
    }

    @Override
    public CSVParser parse(File file) {
        return null;
    }

    @Override
    public boolean validate(CSVParser csvParser) {
        return false;
    }
}