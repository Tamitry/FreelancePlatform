package by.tarlikovski.freelance.dao.mysql;

import java.sql.Connection;

public abstract class AbstractDAO {
    private Connection connection;

    public void setConnection(final Connection con) {
        this.connection = con;
    }

    public Connection getConnection() {
        return connection;
    }
}
