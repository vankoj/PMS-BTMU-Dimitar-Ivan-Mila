package io;

import java.sql.*;

public class DatabaseManager {
    private static DatabaseManager instance = null;
    private Connection connection;

    public static DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();

        return instance;
    }


    private DatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/",
                            "postgres", "mdi");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void dispose() throws SQLException {
        connection.close();
        instance = null;
    }
}
