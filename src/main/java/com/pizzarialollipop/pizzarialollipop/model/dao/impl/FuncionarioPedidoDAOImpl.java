package com.pizzarialollipop.pizzarialollipop.model.dao.impl;

import com.pizzarialollipop.pizzarialollipop.model.dao.FuncionarioPedidoDAO;
import com.pizzarialollipop.pizzarialollipop.model.entities.FuncionarioPedido;
import com.pizzarialollipop.pizzarialollipop.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioPedidoDAOImpl implements FuncionarioPedidoDAO {

    private static final String INSERIR_SQL = "INSERT INTO funcionario_pedido (id_funcionario, id_pedido) VALUES (?, ?)";
    private static final String SELECIONAR_POR_ID_SQL = "SELECT * FROM funcionario_pedido WHERE id = ?";
    private static final String SELECIONAR_TODOS_SQL = "SELECT * FROM funcionario_pedido";
    private static final String DELETAR_SQL = "DELETE FROM funcionario_pedido WHERE id = ?";

    @Override
    public void create(FuncionarioPedido funcionarioPedido) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(INSERIR_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, funcionarioPedido.getIdFuncionario());
            stmt.setInt(2, funcionarioPedido.getIdPedido());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    funcionarioPedido.setId(rs.getInt(1));
                }
            }
            System.out.println("Funcionário-Pedido registrado com sucesso: " + funcionarioPedido);

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar funcionário-pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public FuncionarioPedido read(int id) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_POR_ID_SQL)) {

            stmt.setInt(1, id);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    return new FuncionarioPedido(
                            resultado.getInt("id"),
                            resultado.getInt("id_funcionario"),
                            resultado.getInt("id_pedido")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário-pedido: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FuncionarioPedido> readAll() {
        List<FuncionarioPedido> lista = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_TODOS_SQL);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                lista.add(new FuncionarioPedido(
                        resultado.getInt("id"),
                        resultado.getInt("id_funcionario"),
                        resultado.getInt("id_pedido")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os funcionários-pedidos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void delete(int id) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(DELETAR_SQL)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Funcionário-Pedido deletado com sucesso: ID " + id);
            } else {
                System.out.println("Funcionário-Pedido não encontrado para exclusão: ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar funcionário-pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
