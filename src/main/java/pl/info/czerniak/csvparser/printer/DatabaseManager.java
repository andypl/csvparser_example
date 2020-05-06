package pl.info.czerniak.csvparser.printer;

import org.postgresql.copy.CopyIn;
import org.postgresql.copy.CopyManager;
import org.postgresql.jdbc.PgConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class will act like a DAO layer.
 * It return an copyIn object for writing to postgres using COPY API.
 */
public class DatabaseManager {

    private static final String POSTGRES_COPY_COMMAND = "COPY test FROM STDIN WITH DELIMITER ','";

    PgConnection pgConnection;
    Connection connection;
    CopyManager manager;
    CopyIn copyIn;

    //TODO Add argument for table name instead of hardcoded one.
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
