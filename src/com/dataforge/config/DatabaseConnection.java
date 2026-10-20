package com.dataforge.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton Database Connection Manager.
 * Encapsulates driver registration, configuration loading, and connection lifecycle.
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private String url;
    private String user;
    private String password;

    private DatabaseConnection() {
        loadProperties();
    }

    private void loadProperties() {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream("db.properties")) {
            props.load(in);
            this.url = props.getProperty("db.url", "jdbc:mysql://127.0.0.1:3306/hr_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
            this.user = props.getProperty("db.user", "root");
            this.password = props.getProperty("db.password", "12345678");
        } catch (Exception e) {
            // Fallback defaults
            this.url = "jdbc:mysql://127.0.0.1:3306/hr_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            this.user = "root";
            this.password = "12345678";
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.user, this.password);
    }

    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Database Connection Failed: " + e.getMessage());
            return false;
        }
    }
}
