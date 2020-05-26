package pl.info.czerniak.csvparser.main;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import pl.info.czerniak.csvparser.converter.ConverterImpl;
import pl.info.czerniak.csvparser.parser.ConnectionManager;
import pl.info.czerniak.csvparser.parser.DatabaseConfig;
import pl.info.czerniak.csvparser.parser.ParserFacade;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        final String POSTGRES_COPY_COMMAND = "COPY test FROM STDIN WITH DELIMITER ','";
        String inputFile = System.getProperty("user.dir")+"/doc/sample/input_long.csv";
        File file = new File(inputFile);
        DatabaseConfig databaseConfig = DatabaseConfig.Builder.builder()
                .jdbcURL("jdbc:postgresql://localhost:5432/csvparser_test")
                .driverClassName("org.postgresql.Driver")
                .username("andy")
                .password("andy")
                .build();

        CSVParser csvParser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.DEFAULT);
        Connection connection = ConnectionManager.getInstance(databaseConfig).getConnection();

        ParserFacade parserFacade = new ParserFacade(csvParser, connection, POSTGRES_COPY_COMMAND, new ConverterImpl());
        System.out.println("Printed to database: " + parserFacade.write() + " records.");
    }


}
