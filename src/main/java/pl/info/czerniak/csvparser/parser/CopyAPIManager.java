package pl.info.czerniak.csvparser.parser;

import org.postgresql.copy.CopyIn;
import org.postgresql.copy.CopyManager;
import org.postgresql.jdbc.PgConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class return an copyIn object for given connection object.
 */
class CopyAPIManager {

    private PgConnection pgConnection;
    private Connection connection;
    private org.postgresql.copy.CopyManager manager;
    private CopyIn copyIn;

    CopyAPIManager(Connection connection, String sql) throws SQLException{
        this.connection = connection;
        try {
            this.pgConnection = this.connection.unwrap(PgConnection.class);
        } catch (SQLException e) {
            throw new SQLException("Cannot unwrap connection!");
        }
        this.manager = new CopyManager(pgConnection);
        try {
            this.copyIn = manager.copyIn(sql);
        } catch (SQLException e) {
            throw new SQLException("Cannot create CopyAPI using command: " + sql);
        }
    }

    CopyIn getCopyAPIForConnection() {
        return this.copyIn;
    }

}
