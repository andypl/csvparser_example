package pl.info.czerniak.csvparser.printer;

import org.apache.commons.csv.CSVParser;

import java.sql.Connection;
import java.sql.SQLException;

public class PrinterService {

    private CSVParser csvParser;
    private Printer printer;
    private Connection connection;

    public PrinterService(CSVParser csvParser, Converter converter){
        try {
            this.connection = ConnectionManager.getInstance().getConnection();
            printer = new Printer(csvParser, connection, converter);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public long print(){
        try {
            return this.printer.print();
        } catch (SQLException e) {
            e.getMessage();
        }
        return 0;
    }
}