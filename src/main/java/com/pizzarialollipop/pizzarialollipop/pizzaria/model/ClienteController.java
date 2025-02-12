package com.pizzarialollipop.pizzarialollipop.pizzaria.model;

import com.pizzarialollipop.pizzarialollipop.model.dao.impl.ClienteDAOImpl;
import com.pizzarialollipop.pizzarialollipop.model.entities.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClienteController {

    @FXML private TextField txtNome;
    @FXML private TextField txtTelefone;
    @FXML private TableView<Cliente> tabelaClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNome;
    @FXML private TableColumn<Cliente, String> colTelefone;

    private ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
    private ObservableList<Cliente> listaClientes;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome()));
        colTelefone.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTelefone()));

        carregarClientes();
    }

    private void carregarClientes() {
        listaClientes = FXCollections.observableArrayList(clienteDAO.readAll());
        tabelaClientes.setItems(listaClientes);
    }

    @FXML
    private void adicionarCliente() {
        String nome = txtNome.getText();
        String telefone = txtTelefone.getText();

        if (nome.isEmpty() || telefone.isEmpty()) {
            mostrarAlerta("Erro", "Todos os campos devem ser preenchidos!");
            return;
        }

        Cliente novoCliente = new Cliente(0, nome, telefone);
        clienteDAO.create(novoCliente);
        carregarClientes();
        limparCampos();
    }

    @FXML
    private void atualizarCliente() {
        Cliente clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado == null) {
            mostrarAlerta("Erro", "Selecione um cliente para atualizar!");
            return;
        }

        clienteSelecionado.setNome(txtNome.getText());
        clienteSelecionado.setTelefone(txtTelefone.getText());
        clienteDAO.update(clienteSelecionado);
        carregarClientes();
        limparCampos();
    }

    @FXML
    private void deletarCliente() {
        Cliente clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado == null) {
            mostrarAlerta("Erro", "Selecione um cliente para deletar!");
            return;
        }

        clienteDAO.delete(clienteSelecionado.getId());
        carregarClientes();
    }

    private void limparCampos() {
        txtNome.clear();
        txtTelefone.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
