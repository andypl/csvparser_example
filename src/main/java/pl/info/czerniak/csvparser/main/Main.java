package pl.info.czerniak.csvparser.main;

import pl.info.czerniak.csvparser.printer.ConverterImpl;
import pl.info.czerniak.csvparser.printer.PrinterService;
import pl.info.czerniak.csvparser.reader.ReaderService;

import javax.management.AttributeNotFoundException;
import javax.naming.directory.NoSuchAttributeException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        String inputFile = System.getProperty("user.dir")+"/doc/sample/input.csv";
        ReaderService readerService = new ReaderService();
        PrinterService printerService = new PrinterService(readerService.getDefaultParserForFile(inputFile), new ConverterImpl());
        System.out.println(printerService.print());
    }
}
