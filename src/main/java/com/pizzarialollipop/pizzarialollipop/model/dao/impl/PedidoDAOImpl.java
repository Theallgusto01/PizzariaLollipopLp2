package com.pizzarialollipop.pizzarialollipop.model.dao.impl;

import com.pizzarialollipop.pizzarialollipop.factory.ConnectionFactory;
import com.pizzarialollipop.pizzarialollipop.model.dao.PedidoDAO;
import com.pizzarialollipop.pizzarialollipop.model.entities.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    private static final String INSERIR_SQL = "INSERT INTO pedido (valor_total, forma_pagamento, troco, data_hora, id_cliente) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECIONAR_POR_ID_SQL = "SELECT id_pedido, valor_total, forma_pagamento, troco, data_hora, id_cliente FROM pedido WHERE id_pedido = ?";
    private static final String SELECIONAR_TODOS_SQL = "SELECT id_pedido, valor_total, forma_pagamento, troco, data_hora, id_cliente FROM pedido";
    private static final String ATUALIZAR_SQL = "UPDATE pedido SET valor_total = ?, forma_pagamento = ?, troco = ?, data_hora = ?, id_cliente = ? WHERE id_pedido = ?";
    private static final String DELETAR_SQL = "DELETE FROM pedido WHERE id_pedido = ?";

    @Override
    public void create(Pedido pedido) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(INSERIR_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, pedido.getValor_total());
            stmt.setString(2, pedido.getForma_pagamento());
            stmt.setDouble(3, pedido.getTroco());
            stmt.setTimestamp(4, Timestamp.valueOf(pedido.getData_hora()));
            stmt.setInt(5, pedido.getId_cliente());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        pedido.setId_pedido(rs.getInt(1));
                    }
                }
            }
            System.out.println("Pedido adicionado com sucesso: " + pedido);

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar pedido: " + e.getMessage());
        }
    }

    @Override
    public Pedido read(int id) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_POR_ID_SQL)) {

            stmt.setInt(1, id);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    return new Pedido(
                            resultado.getInt("id_pedido"),
                            resultado.getDouble("valor_total"),
                            resultado.getString("forma_pagamento"),
                            resultado.getDouble("troco"),
                            resultado.getTimestamp("data_hora").toLocalDateTime(),
                            resultado.getInt("id_cliente")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar pedido: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Pedido> readAll() {
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_TODOS_SQL);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                pedidos.add(new Pedido(
                        resultado.getInt("id_pedido"),
                        resultado.getDouble("valor_total"),
                        resultado.getString("forma_pagamento"),
                        resultado.getDouble("troco"),
                        resultado.getTimestamp("data_hora").toLocalDateTime(),
                        resultado.getInt("id_cliente")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    @Override
    public void update(Pedido pedido) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(ATUALIZAR_SQL)) {

            stmt.setDouble(1, pedido.getValor_total());
            stmt.setString(2, pedido.getForma_pagamento());
            stmt.setDouble(3, pedido.getTroco());
            stmt.setTimestamp(4, Timestamp.valueOf(pedido.getData_hora()));
            stmt.setInt(5, pedido.getId_cliente());
            stmt.setInt(6, pedido.getId_pedido());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Pedido atualizado com sucesso: " + pedido);
            } else {
                System.out.println("Pedido não encontrado para atualização: ID " + pedido.getId_pedido());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pedido: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(DELETAR_SQL)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Pedido deletado com sucesso: ID " + id);
            } else {
                System.out.println("Pedido não encontrado para exclusão: ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar pedido: " + e.getMessage());
        }
    }
}
