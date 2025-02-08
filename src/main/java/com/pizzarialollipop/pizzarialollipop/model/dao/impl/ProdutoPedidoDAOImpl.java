package com.pizzarialollipop.pizzarialollipop.model.dao.impl;

import com.pizzarialollipop.pizzarialollipop.factory.ConnectionFactory;
import com.pizzarialollipop.pizzarialollipop.model.dao.ProdutoPedidoDAO;
import com.pizzarialollipop.pizzarialollipop.model.entities.ProdutoPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoPedidoDAOImpl implements ProdutoPedidoDAO {

    private static final String INSERIR_SQL = "INSERT INTO produto_pedido (id_produto, id_pedido, quantidade) VALUES (?, ?, ?)";
    private static final String SELECIONAR_POR_ID_SQL = "SELECT id_produto, id_pedido, quantidade FROM produto_pedido WHERE id_produto = ? AND id_pedido = ?";
    private static final String SELECIONAR_TODOS_SQL = "SELECT id_produto, id_pedido, quantidade FROM produto_pedido";
    private static final String ATUALIZAR_SQL = "UPDATE produto_pedido SET quantidade = ? WHERE id_produto = ? AND id_pedido = ?";
    private static final String DELETAR_SQL = "DELETE FROM produto_pedido WHERE id_produto = ? AND id_pedido = ?";

    @Override
    public void create(ProdutoPedido produtoPedido) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(INSERIR_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, produtoPedido.getId_produto());
            stmt.setInt(2, produtoPedido.getId_pedido());
            stmt.setInt(3, produtoPedido.getQuantidade());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Caso necessário, definir o ID gerado no caso de um relacionamento de chave composta.
                    }
                }
            }
            System.out.println("ProdutoPedido adicionado com sucesso: " + produtoPedido);

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar ProdutoPedido: " + e.getMessage());
        }
    }

    @Override
    public ProdutoPedido read(int id_produto, int id_pedido) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_POR_ID_SQL)) {

            stmt.setInt(1, id_produto);
            stmt.setInt(2, id_pedido);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    return new ProdutoPedido(
                            resultado.getInt("id_produto"),
                            resultado.getInt("id_pedido"),
                            resultado.getInt("quantidade")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar ProdutoPedido: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ProdutoPedido> readAll() {
        List<ProdutoPedido> produtoPedidos = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_TODOS_SQL);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                produtoPedidos.add(new ProdutoPedido(
                        resultado.getInt("id_produto"),
                        resultado.getInt("id_pedido"),
                        resultado.getInt("quantidade")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os ProdutoPedidos: " + e.getMessage());
        }
        return produtoPedidos;
    }

    @Override
    public void update(ProdutoPedido produtoPedido) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(ATUALIZAR_SQL)) {

            stmt.setInt(1, produtoPedido.getQuantidade());
            stmt.setInt(2, produtoPedido.getId_produto());
            stmt.setInt(3, produtoPedido.getId_pedido());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("ProdutoPedido atualizado com sucesso: " + produtoPedido);
            } else {
                System.out.println("ProdutoPedido não encontrado para atualização: ID Produto " + produtoPedido.getId_produto() + ", ID Pedido " + produtoPedido.getId_pedido());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar ProdutoPedido: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id_produto, int id_pedido) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(DELETAR_SQL)) {

            stmt.setInt(1, id_produto);
            stmt.setInt(2, id_pedido);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("ProdutoPedido deletado com sucesso: Produto ID " + id_produto + ", Pedido ID " + id_pedido);
            } else {
                System.out.println("ProdutoPedido não encontrado para exclusão: Produto ID " + id_produto + ", Pedido ID " + id_pedido);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar ProdutoPedido: " + e.getMessage());
        }
    }
}
