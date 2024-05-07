package org.revhire.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static volatile Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (DatabaseConnection.class) {
                if (connection == null || connection.isClosed()) {
                    // Load the JDBC driver
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    } catch (ClassNotFoundException e) {
                        throw new SQLException("JDBC driver not found", e);
                    }

                    // Define the JDBC URL
                    String url = "jdbc:sqlserver://localhost;databaseName=Revhire;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";


                    connection = DriverManager.getConnection(url);
                }
            }
        }
        return connection;


    }
}
