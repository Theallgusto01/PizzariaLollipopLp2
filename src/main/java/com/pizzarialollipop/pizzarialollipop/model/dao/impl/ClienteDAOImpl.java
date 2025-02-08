package com.pizzarialollipop.pizzarialollipop.model.dao.impl;

import com.pizzarialollipop.pizzarialollipop.factory.ConnectionFactory;
import com.pizzarialollipop.pizzarialollipop.model.dao.ClienteDAO;
import com.pizzarialollipop.pizzarialollipop.model.entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private static final String INSERIR_SQL = "INSERT INTO cliente (nome_cliente, telefone) VALUES (?, ?)";
    private static final String SELECIONAR_POR_ID_SQL = "SELECT id_cliente, nome_cliente, telefone FROM cliente WHERE id_cliente = ?";
    private static final String SELECIONAR_TODOS_SQL = "SELECT id_cliente, nome_cliente, telefone FROM cliente";
    private static final String ATUALIZAR_SQL = "UPDATE cliente SET nome_cliente = ?, telefone = ? WHERE id_cliente = ?";
    private static final String DELETAR_SQL = "DELETE FROM cliente WHERE id_cliente = ?";

    @Override
    public void create(Cliente cliente) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(INSERIR_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        cliente.setId(rs.getInt(1));
                    }
                }
                System.out.println("Cliente adicionado com sucesso: " + cliente);
            } else {
                System.err.println("Falha ao adicionar cliente.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar cliente: " + e.getMessage(), e);
        }
    }   

    @Override
    public Cliente read(int id) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_POR_ID_SQL)) {

            stmt.setInt(1, id);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    return new Cliente(
                            resultado.getInt("id_cliente"),
                            resultado.getString("nome_cliente"),
                            resultado.getString("telefone")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Cliente> readAll() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_TODOS_SQL);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                clientes.add(new Cliente(
                        resultado.getInt("id_cliente"),
                        resultado.getString("nome_cliente"),
                        resultado.getString("telefone")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os clientes: " + e.getMessage(), e);
        }
        return clientes;
    }

    @Override
    public void update(Cliente cliente) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(ATUALIZAR_SQL)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setInt(3, cliente.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente atualizado com sucesso: " + cliente);
            } else {
                System.err.println("Cliente não encontrado para atualização: ID " + cliente.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(DELETAR_SQL)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Cliente deletado com sucesso: ID " + id);
            } else {
                System.err.println("Cliente não encontrado para exclusão: ID " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage(), e);
        }
    }
}
