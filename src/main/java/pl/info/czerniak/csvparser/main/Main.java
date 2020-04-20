package pl.info.czerniak.csvparser.main;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import pl.info.czerniak.csvparser.exception.IncorrectColNumberFileException;
import pl.info.czerniak.csvparser.parser.CSVFileParserFactory;
import pl.info.czerniak.csvparser.parser.CSVValidator;
import pl.info.czerniak.csvparser.parser.FactoryType;
import pl.info.czerniak.csvparser.parser.ParserFactory;
import pl.info.czerniak.csvparser.printer.CSVFilePrinterFactory;
import pl.info.czerniak.csvparser.printer.FilePrinterFactory;
import pl.info.czerniak.csvparser.printer.PrinterFactory;

import javax.naming.directory.NoSuchAttributeException;
import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) throws NoSuchAttributeException, IncorrectColNumberFileException, IOException {

        String inputFile = System.getProperty("user.dir")+"/doc/sample/input.csv";
        String outputFile = System.getProperty("user.dir")+"/doc/sample/output.csv";

        ParserFactory parserFactory = ParserFactory.getInstance(FactoryType.FILE_FACTORY);
        CSVFileParserFactory fileParserFactory = parserFactory.getCSVFileParserFactory();
        CSVParser csvParser = fileParserFactory.getParserForFileName(inputFile, new CSVValidator(Charset.defaultCharset(), 10, false, CSVFormat.DEFAULT.withHeader()));
        FilePrinterFactory filePrinterFactory = PrinterFactory.getInstance(FactoryType.FILE_FACTORY);
        CSVFilePrinterFactory csvFilePrinterFactory = filePrinterFactory.getCSVFilePrinterFactory();
        csvFilePrinterFactory.printToFile(csvParser,CSVFormat.POSTGRESQL_CSV, outputFile);
    }
}
