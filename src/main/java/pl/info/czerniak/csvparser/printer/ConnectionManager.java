package pl.info.czerniak.csvparser.printer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class provide datasource for Postgresql database. It will return connection from pool.
 * It use HikariCP library.
 */
public class ConnectionManager {

    private static ConnectionManager connectionManager;
    private HikariDataSource dataSource;

    //TODO Move Hikari config to a properties.
    private ConnectionManager(){
        org.apache.log4j.BasicConfigurator.configure();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/csvparser_test");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("andy");
        config.setPassword("andy");
        dataSource = new HikariDataSource(config);
    }

     Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    void close(){
        if(dataSource != null){
            dataSource.close();
        }
    }

    static ConnectionManager getInstance(){
        if(connectionManager == null){
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }
}