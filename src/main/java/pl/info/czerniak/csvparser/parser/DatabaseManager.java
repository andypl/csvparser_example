package pl.info.czerniak.csvparser.parser;

import org.postgresql.copy.CopyIn;
import org.postgresql.copy.CopyManager;
import org.postgresql.jdbc.PgConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class return an copyIn object for given connection object.
 */
class DatabaseManager {

    private static final String POSTGRES_COPY_COMMAND = "COPY test FROM STDIN WITH DELIMITER ','";

    private PgConnection pgConnection;
    private Connection connection;
    private CopyManager manager;
    private CopyIn copyIn;

    //TODO Add argument for table name instead of using hardcoded one.
    DatabaseManager(Connection connection) throws SQLException {
        this.connection = connection;
        this.pgConnection = this.connection.unwrap(PgConnection.class);
        this.manager = new CopyManager(pgConnection);
        this.copyIn = manager.copyIn(POSTGRES_COPY_COMMAND);
    }

    CopyIn getCopyIn() {
        return this.copyIn;
    }
}
