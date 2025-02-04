package com.pizzarialollipop.pizzarialollipop.model.dao.impl;

import com.pizzarialollipop.pizzarialollipop.model.dao.ProdutoDAO;
import com.pizzarialollipop.pizzarialollipop.pizzaria.model.Pizza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {
    private static final String DB_URL = "jdbc:mysql:pizzaria_BD"; // Substitua pelo seu banco de dados
    private static final String INSERT_SQL = "INSERT INTO produto (id_p roduto, nome_produto, valor_produto) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM produto WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM produto";
    private static final String UPDATE_SQL = "UPDATE pizza SET name = ?, price = ?, description = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM pizza WHERE id = ?";

    static {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS pizza (" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "price REAL NOT NULL," +
                    "description TEXT)";
            statement.execute(createTableSQL);
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    @Override
    public void create(Pizza pizza) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {

            preparedStatement.setInt(1, pizza.getId());
            preparedStatement.setString(2, pizza.getName());
            preparedStatement.setDouble(3, pizza.getPrice());
            preparedStatement.executeUpdate();
            System.out.println("Pizza added: " + pizza);

        } catch (SQLException e) {
            System.err.println("Error adding pizza: " + e.getMessage());
        }
    }

    @Override
    public Pizza read(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Pizza(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error reading pizza: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Pizza> readAll() {
        List<Pizza> pizzas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                pizzas.add(new Pizza(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error reading all pizzas: " + e.getMessage());
        }
        return pizzas;
    }

    @Override
    public void update(Pizza pizza) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setString(1, pizza.getName());
            preparedStatement.setDouble(2, pizza.getPrice());
            preparedStatement.setInt(3, pizza.getId());

            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Pizza updated: " + pizza);
            } else {
                System.out.println("Pizza not found: ID " + pizza.getId());
            }

        } catch (SQLException e) {
            System.err.println("Error updating pizza: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Pizza deleted: ID " + id);
            } else {
                System.out.println("Pizza not found: ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error deleting pizza: " + e.getMessage());
        }
    }
}
