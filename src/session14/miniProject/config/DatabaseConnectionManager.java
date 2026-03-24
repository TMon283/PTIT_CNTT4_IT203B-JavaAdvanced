package session14.miniProject.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/session14_flash_sale";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    private static DatabaseConnectionManager instance;

    private final String url;
    private final String user;
    private final String password;

    private DatabaseConnectionManager() {
        this.url = System.getenv().getOrDefault("FLASH_DB_URL", URL);
        this.user = System.getenv().getOrDefault("FLASH_DB_USER", USER);
        this.password = System.getenv().getOrDefault("FLASH_DB_PASSWORD", PASSWORD);
    }

    public static synchronized DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
