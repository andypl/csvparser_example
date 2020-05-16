package pl.info.czerniak.csvparser.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

class Parser {
    static CSVParser getDefaultCSVParser(File file) throws IOException {
        return CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.DEFAULT);
    }
}
