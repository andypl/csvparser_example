package pl.info.czerniak.csvparser.parser;

import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ParserFacade {
    private File file;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private CSVParser csvParser;
    private Connection connection;
    private Converter converter;
    private DatabaseManager databaseManager;

    public ParserFacade(String fileName, DatabaseConfig databaseConfig, Converter converter){
        this.file = openFile(fileName);
        createDefaultParserForFile();
        setConnection(databaseConfig);
        this.converter = converter;
    }

    private File openFile(String fileName){
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.getLocalizedMessage();
        } catch (EmptyFileException e) {
            e.getLocalizedMessage();
        }
        return fileReader.getFile();
    }

   private void createDefaultParserForFile(){
       try {
           this.csvParser = Parser.getDefaultCSVParser(this.file);
       } catch (IOException e) {
           e.getLocalizedMessage();
       }
   }

   private void setConnection(DatabaseConfig databaseConfig){
        try {
           this.connection = ConnectionManager.getInstance(databaseConfig).getConnection();
       } catch (SQLException e) {
           e.getLocalizedMessage();
       }
   }

   public long write(){
       try {
           this.fileWriter = new FileWriter(this.csvParser, this.connection, this.converter);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       try {
           return fileWriter.write();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return 0;
   }
}
