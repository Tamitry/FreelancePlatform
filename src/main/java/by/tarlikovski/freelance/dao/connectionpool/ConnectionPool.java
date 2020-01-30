package by.tarlikovski.freelance.dao.connectionpool;

import by.tarlikovski.freelance.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {


    private String url;
    private String user;
    private String password;
    private int maxSize;
    private int checkConnectionTimeout;

    private static ReentrantLock locker = new ReentrantLock();


    private BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
    private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

    private ConnectionPool() {
    }

    public Connection getConnection() throws DAOException {
        locker.lock();
        PooledConnection connection = null;
        while (connection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if (!connection.isValid(checkConnectionTimeout)) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException e) {
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = createConnection();
                } else {
                    throw new DAOException("The limit of number of database connections is exceeded");
                }
            } catch (InterruptedException | SQLException e) {
                throw new DAOException(e);
            }
        }
        usedConnections.add(connection);
        locker.unlock();
        return connection;
    }

    void freeConnection(PooledConnection connection) {

        locker.lock();
        try {
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
            }
        } catch (SQLException | InterruptedException e1) {
            try {
                connection.getConnection().close();
            } catch (SQLException e2) {
            }
        }
        locker.unlock();
    }

    public void init() throws DAOException {
        locker.lock();
        try {
            destroy();
            ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
            Class.forName(resourceBundle.getString("driver"));
            this.url = resourceBundle.getString("url");
            this.user = resourceBundle.getString("user");
            this.password = resourceBundle.getString("password");
            this.maxSize = Integer.parseInt(resourceBundle.getString("pool_max_size"));
            this.checkConnectionTimeout = Integer.parseInt(resourceBundle.getString("pool_check_connection_timeout"));
            for (int counter = 0; counter < Integer.parseInt(resourceBundle.getString("pool_start_size")); counter++) {
                freeConnections.put(createConnection());
            }

        } catch (ClassNotFoundException | InterruptedException | SQLException e) {
            throw new DAOException(e);
        }
        locker.unlock();
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    private PooledConnection createConnection() throws SQLException {
        return new PooledConnection(DriverManager.getConnection(url, user, password));
    }

    public void destroy() {
        locker.lock();
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (PooledConnection connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
            }
        }
        usedConnections.clear();
        locker.unlock();
    }


}
