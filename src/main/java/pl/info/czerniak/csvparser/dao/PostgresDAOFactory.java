package pl.info.czerniak.csvparser.dao;

import java.sql.SQLException;

public class PostgresDAOFactory extends DAOFactory{
    @Override
    public TestDAO getTestDAO() throws SQLException {
        return new PostgresTestDAOImpl();
    }
}
