package pl.info.czerniak.csvparser.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.PGConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static ConnectionManager connectionManager;
    private HikariDataSource dataSource;

    private ConnectionManager(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/csvparser_test");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("andy");
        config.setPassword("andy");
        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public PGConnection getPGConnection() throws SQLException {
        return dataSource.getConnection().unwrap(PGConnection.class);
    }

    public void close(){
        if(dataSource != null){
            dataSource.close();
        }
    }

    public static ConnectionManager getInstance(){
        if(connectionManager == null){
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }
}