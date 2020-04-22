package pl.info.czerniak.csvparser.parser3;

import org.apache.commons.csv.CSVParser;

import java.io.File;

public class CSVFileParserImpl implements CSVFileParser{
    @Override
    public File readFileForParse(String filName) {
        return null;
    }

    @Override
    public boolean validateFile(File file, CSVFileSpec csvFileSpec) {
        return false;
    }

    @Override
    public CSVParser parse(File file, CSVFileSpec csvFileSpec) {
        return null;
    }
}
