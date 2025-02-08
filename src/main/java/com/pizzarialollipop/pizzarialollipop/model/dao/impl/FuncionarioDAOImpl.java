package com.pizzarialollipop.pizzarialollipop.model.dao.impl;

import com.pizzarialollipop.pizzarialollipop.factory.ConnectionFactory;
import com.pizzarialollipop.pizzarialollipop.model.dao.FuncionarioDAO;
import com.pizzarialollipop.pizzarialollipop.model.entities.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOImpl implements FuncionarioDAO {

    private static final String INSERIR_SQL = "INSERT INTO funcionario (nome_funcionario, cargo) VALUES (?, ?)";
    private static final String SELECIONAR_POR_ID_SQL = "SELECT id_funcionario, nome_funcionario, cargo FROM funcionario WHERE id_funcionario = ?";
    private static final String SELECIONAR_TODOS_SQL = "SELECT id_funcionario, nome_funcionario, cargo FROM funcionario";
    private static final String ATUALIZAR_SQL = "UPDATE funcionario SET nome_funcionario = ?, cargo = ? WHERE id_funcionario = ?";
    private static final String DELETAR_SQL = "DELETE FROM funcionario WHERE id_funcionario = ?";

    @Override
    public void create(Funcionario funcionario) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(INSERIR_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        funcionario.setId(rs.getLong(1));
                    }
                }
                System.out.println("Funcionário adicionado com sucesso: " + funcionario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar funcionário: " + e.getMessage());
        }
    }

    @Override
    public Funcionario read(long id) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_POR_ID_SQL)) {

            stmt.setLong(1, id);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    return new Funcionario(
                            resultado.getLong("id_funcionario"),
                            resultado.getString("nome_funcionario"),
                            resultado.getString("cargo")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Funcionario> readAll() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_TODOS_SQL);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                funcionarios.add(new Funcionario(
                        resultado.getLong("id_funcionario"),
                        resultado.getString("nome_funcionario"),
                        resultado.getString("cargo")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os funcionários: " + e.getMessage());
        }
        return funcionarios;
    }

    @Override
    public void update(Funcionario funcionario) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(ATUALIZAR_SQL)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setLong(3, funcionario.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Funcionário atualizado com sucesso: " + funcionario);
            } else {
                System.out.println("Funcionário não encontrado para atualização: ID " + funcionario.getId());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(DELETAR_SQL)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Funcionário deletado com sucesso: ID " + id);
            } else {
                System.out.println("Funcionário não encontrado para exclusão: ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar funcionário: " + e.getMessage());
        }
    }
}
