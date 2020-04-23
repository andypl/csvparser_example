package pl.info.czerniak.csvparser.main;

import pl.info.czerniak.csvparser.converter.ConverterImpl;
import pl.info.czerniak.csvparser.dao.DatabaseManager;
import pl.info.czerniak.csvparser.database.ConnectionManager;
import pl.info.czerniak.csvparser.exception.IncorrectColNumberFileException;
import pl.info.czerniak.csvparser.parser3.CSVFileParserImpl;
import pl.info.czerniak.csvparser.service.ParserService;

import javax.management.AttributeNotFoundException;
import javax.naming.directory.NoSuchAttributeException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws NoSuchAttributeException, IncorrectColNumberFileException, IOException, SQLException, AttributeNotFoundException {

        String inputFile = System.getProperty("user.dir")+"/doc/sample/input.csv";

        List<String> headerList = Stream.of("Header1", "Header2", "Header3", "Header4", "Header5","Header6", "Header7", "Header8", "Header9", "Header10")
                .collect(Collectors.toList());

        ParserService parserService = new ParserService(inputFile, headerList);
        DatabaseManager databaseManager = new DatabaseManager(ConnectionManager.getInstance().getConnection());
        ConverterImpl converter = new ConverterImpl(parserService.getCSVParser(), databaseManager.getCopyIn());
        converter.convert();
        System.out.println("Total rows in file: " + converter.getRows());
        System.out.println("Rows converted: " + converter.getConvertedRows());
    }
}
