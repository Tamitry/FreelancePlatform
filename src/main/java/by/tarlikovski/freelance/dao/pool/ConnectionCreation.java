package by.tarlikovski.freelance.dao.pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionCreation {
    public static Connection createConnection(final String log,
                                              final String pass)
            throws SQLException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("application.properties"));
        return DriverManager.getConnection(properties
                .getProperty("connection"), log, pass);
    }
}
