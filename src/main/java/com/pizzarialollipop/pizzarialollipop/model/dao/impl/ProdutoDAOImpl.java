package com.pizzarialollipop.pizzarialollipop.model.dao.impl;

import com.pizzarialollipop.pizzarialollipop.factory.ConnectionFactory;
import com.pizzarialollipop.pizzarialollipop.model.dao.ProdutoDAO;
import com.pizzarialollipop.pizzarialollipop.model.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {

    private static final String INSERIR_SQL = "INSERT INTO produto (nome_produto, valor_produto) VALUES (?, ?)";
    private static final String SELECIONAR_POR_ID_SQL = "SELECT id_produto, nome_produto, valor_produto FROM produto WHERE id_produto = ?";
    private static final String SELECIONAR_TODOS_SQL = "SELECT id_produto, nome_produto, valor_produto FROM produto";
    private static final String ATUALIZAR_SQL = "UPDATE produto SET nome_produto = ?, valor_produto = ? WHERE id_produto = ?";
    private static final String DELETAR_SQL = "DELETE FROM produto WHERE id_produto = ?";

    @Override
    public void create(Produto produto) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(INSERIR_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome_produto());
            stmt.setDouble(2, produto.getValor_produto());

                                            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        produto.setId_produto(rs.getInt(1));
                    }
                }
            }
            System.out.println("Produto adicionado com sucesso: " + produto);

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    @Override
    public Produto read(int id) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_POR_ID_SQL)) {

            stmt.setInt(1, id);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    return new Produto(
                            resultado.getInt("id_produto"),
                            resultado.getDouble("valor_produto"),
                            resultado.getString("nome_produto")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Produto> readAll() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_TODOS_SQL);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                produtos.add(new Produto(
                        resultado.getInt("id_produto"),
                        resultado.getDouble("valor_produto"),
                        resultado.getString("nome_produto")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os produtos: " + e.getMessage());
        }
        return produtos;
    }

    @Override
    public void update(Produto produto) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(ATUALIZAR_SQL)) {

            stmt.setString(1, produto.getNome_produto());
            stmt.setDouble(2, produto.getValor_produto());
            stmt.setInt(3, produto.getId_produto());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto atualizado com sucesso: " + produto);
            } else {
                System.out.println("Produto não encontrado para atualização: ID " + produto.getId_produto());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(DELETAR_SQL)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto deletado com sucesso: ID " + id);
            } else {
                System.out.println("Produto não encontrado para exclusão: ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
