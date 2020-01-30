package by.tarlikovski.freelance.dao.mysql;

import java.sql.Connection;

abstract public class BaseDaoImpl {
    protected Connection connection;

    public void setConnection(final Connection con) {
        this.connection = con;
    }
}
