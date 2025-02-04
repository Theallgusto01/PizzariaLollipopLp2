package com.pizzarialollipop.pizzarialollipop.factory;

import java.sql.*;
import java.util.Scanner;

// Database Connection Factory
class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/pizzaria_lollipop";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}