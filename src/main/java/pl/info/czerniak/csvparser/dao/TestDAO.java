package pl.info.czerniak.csvparser.dao;

import org.apache.commons.csv.CSVParser;

import java.sql.SQLException;

public interface TestDAO {
    public void create(CSVParser csvParser) throws SQLException;
}
