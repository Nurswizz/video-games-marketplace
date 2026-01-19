package src.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {
    private static final String url = EnvLoader.get("DB_URL");
    private static final String user = EnvLoader.get("DB_USER");
    private static final String password = EnvLoader.get("DB_PASSWORD");

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
