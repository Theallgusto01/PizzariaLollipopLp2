package com.pizzarialollipop.pizzarialollipop.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/pizzaria_BD";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    private ConnectionFactory() {}


    // Método para obter conexão única
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
