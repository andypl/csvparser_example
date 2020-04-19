package pl.info.czerniak.csvparser.validators;

import pl.info.czerniak.csvparser.exception.EmptyFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

public class FileValidator {

    private FileValidator() {}

    public static File validateInputFile(String fileName) throws FileNotFoundException, EmptyFileException {
        File file = new File(fileName);
        if(!file.exists()){
            throw new FileNotFoundException("File with that name not exist!");
        }else if(file.length() == 0){
            throw new EmptyFileException("File is empty!");
        }
        return file;
    }

    public static File validateOutputFile(String fileName) throws FileAlreadyExistsException {
        File file = new File(fileName);
        if(file.exists()){
            throw new FileAlreadyExistsException("Output file with specified name already exist!");
        }
        return file;
    }
}