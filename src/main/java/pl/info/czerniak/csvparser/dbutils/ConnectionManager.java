package pl.info.czerniak.csvparser.dbutils;

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

    private ConnectionManager(DatabaseConfig databaseConfig){
        org.apache.log4j.BasicConfigurator.configure();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(databaseConfig.getJdbcURL());
        config.setDriverClassName(databaseConfig.getDriverClassName());
        config.setUsername(databaseConfig.getUsername());
        config.setPassword(databaseConfig.getPassword());
        dataSource = new HikariDataSource(config);
    }

     public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    void close(){
        if(dataSource != null){
            dataSource.close();
        }
    }

    public static ConnectionManager getInstance(DatabaseConfig databaseConfig){
        if(connectionManager == null){
            connectionManager = new ConnectionManager(databaseConfig);
        }
        return connectionManager;
    }
}