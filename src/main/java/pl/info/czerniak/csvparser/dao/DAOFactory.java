package pl.info.czerniak.csvparser.dao;

import java.sql.SQLException;

public abstract class DAOFactory {
    public abstract TestDAO getTestDAO() throws SQLException;
}
