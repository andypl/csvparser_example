package pl.info.czerniak.csvparser.parser;

import java.io.File;
import java.io.FileNotFoundException;

class FileReader {

    private File file;

    FileReader(String fileName) throws FileNotFoundException, EmptyFileException {
        this.file = openFile(fileName);
    }

    private File openFile(String fileName) throws FileNotFoundException, EmptyFileException {
        File file = new File(fileName);
        if(!file.exists()){
            throw new FileNotFoundException(file.getAbsolutePath());
        }else if(file.length() == 0){
            throw new EmptyFileException(file.getAbsolutePath());
        }
        return file;
    }

    File getFile(){
        return this.file;
    }
}
