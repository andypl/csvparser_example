package pl.info.czerniak.csvparser.parser;

import org.apache.commons.csv.CSVParser;

import java.sql.Connection;

public class ParserFacade {
    private String sql;
    private FileWriter fileWriter;
    private CSVParser csvParser;
    private Connection connection;
    private Converter converter;

    public ParserFacade(CSVParser csvParser, Connection connection, String sql, Converter converter){
        this.connection = connection;
        this.csvParser = csvParser;
        this.converter = converter;
        this.sql = sql;
    }

   public long write(){
       this.fileWriter = new FileWriter(this.csvParser, this.connection, this.sql, this.converter);
       try {
           return fileWriter.write();
       } catch (SQLWriteException | SQLEndCopyException e) {
           System.err.println(e.getMessage());
       }
       return 0;
   }
}
