package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {
    private static final int MAX_CONNECTIONS = 5;
    private final Queue<Connection> pool = new LinkedList<>();

    public ConnectionPool() throws SQLException {
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            pool.add(Database.getConnection());
        }
    }

    public synchronized Connection getConnection() {
        return pool.poll();
    }

    public synchronized void releaseConnection(Connection connection) {
        pool.offer(connection);
    }
}
