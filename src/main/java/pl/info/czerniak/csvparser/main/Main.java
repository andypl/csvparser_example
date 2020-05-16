package pl.info.czerniak.csvparser.main;

import pl.info.czerniak.csvparser.converter.ConverterImpl;
import pl.info.czerniak.csvparser.parser.DatabaseConfig;
import pl.info.czerniak.csvparser.parser.ParserFacade;


public class Main {

    public static void main(String[] args) {
        String inputFile = System.getProperty("user.dir")+"/doc/sample/input_long.csv";
        DatabaseConfig databaseConfig = DatabaseConfig.Builder.builder()
                .jdbcURL("jdbc:postgresql://localhost:5432/csvparser_test")
                .driverClassName("org.postgresql.Driver")
                .username("andy")
                .password("andy")
                .build();
        ParserFacade parserFacade = new ParserFacade(inputFile, databaseConfig, new ConverterImpl());
        System.out.println("Printed to database: " + parserFacade.write() + " records.");
    }


}
