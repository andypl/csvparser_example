package pl.info.czerniak.csvparser.dao;

public class PostgresDAOFactory extends DAOFactory{
    @Override
    public TestDAO getTestDAO() {
        return new PostgresTestDAOImpl();
    }
}
