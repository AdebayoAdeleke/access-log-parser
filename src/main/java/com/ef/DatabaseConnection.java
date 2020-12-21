package com.ef;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DatabaseConnection {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/logdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connectionInstance;

    private DatabaseConnection(){}


    public static Connection getConnection() throws SQLException {

        if (connectionInstance != null) {
            return connectionInstance;
        }

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connectionInstance = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        return connectionInstance;
    }
}
