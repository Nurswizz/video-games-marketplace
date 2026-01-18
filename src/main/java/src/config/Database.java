package src.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    final String url = EnvLoader.get("DB_URL");
    final String user = EnvLoader.get("DB_USER");
    final String password = EnvLoader.get("DB_PASSWORD");

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
