package com.pizzarialollipop.pizzarialollipop.pizzaria.model;

import com.pizzarialollipop.pizzarialollipop.model.dao.impl.FuncionarioDAOImpl;
import com.pizzarialollipop.pizzarialollipop.model.entities.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FuncionarioController {

    @FXML private TextField txtNome;
    @FXML private TextField txtCargo;
    @FXML private TableView<Funcionario> tabelaFuncionarios;
    @FXML private TableColumn<Funcionario, Long> colId;
    @FXML private TableColumn<Funcionario, String> colNome;
    @FXML private TableColumn<Funcionario, String> colCargo;

    private FuncionarioDAOImpl funcionarioDAO = new FuncionarioDAOImpl();
    private ObservableList<Funcionario> listaFuncionarios;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleLongProperty(cellData.getValue().getId()).asObject());
        colNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome()));
        colCargo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCargo()));

        carregarFuncionarios();
    }

    private void carregarFuncionarios() {
        listaFuncionarios = FXCollections.observableArrayList(funcionarioDAO.readAll());
        tabelaFuncionarios.setItems(listaFuncionarios);
    }

    @FXML
    private void adicionarFuncionario() {
        String nome = txtNome.getText();
        String cargo = txtCargo.getText();

        if (nome.isEmpty() || cargo.isEmpty()) {
            mostrarAlerta("Erro", "Nome e cargo são obrigatórios!");
            return;
        }

        Funcionario novoFuncionario = new Funcionario(0, nome, cargo);
        funcionarioDAO.create(novoFuncionario);
        carregarFuncionarios();
        limparCampos();
    }

    @FXML
    private void atualizarFuncionario() {
        Funcionario funcionarioSelecionado = tabelaFuncionarios.getSelectionModel().getSelectedItem();
        if (funcionarioSelecionado == null) {
            mostrarAlerta("Erro", "Selecione um funcionário para atualizar!");
            return;
        }

        funcionarioSelecionado.setNome(txtNome.getText());
        funcionarioSelecionado.setCargo(txtCargo.getText());
        funcionarioDAO.update(funcionarioSelecionado);
        carregarFuncionarios();
        limparCampos();
    }

    @FXML
    private void deletarFuncionario() {
        Funcionario funcionarioSelecionado = tabelaFuncionarios.getSelectionModel().getSelectedItem();
        if (funcionarioSelecionado == null) {
            mostrarAlerta("Erro", "Selecione um funcionário para deletar!");
            return;
        }

        funcionarioDAO.delete(funcionarioSelecionado.getId());
        carregarFuncionarios();
    }

    private void limparCampos() {
        txtNome.clear();
        txtCargo.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
