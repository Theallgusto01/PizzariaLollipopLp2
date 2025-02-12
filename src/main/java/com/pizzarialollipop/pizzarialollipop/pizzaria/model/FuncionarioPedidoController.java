package com.pizzarialollipop.pizzarialollipop.pizzaria.model;

import com.pizzarialollipop.pizzarialollipop.model.dao.impl.FuncionarioPedidoDAOImpl;
import com.pizzarialollipop.pizzarialollipop.model.entities.FuncionarioPedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FuncionarioPedidoController {

    @FXML private TableView<FuncionarioPedido> tabelaFuncionarioPedido;
    @FXML private TableColumn<FuncionarioPedido, Integer> colId;
    @FXML private TableColumn<FuncionarioPedido, Integer> colIdFuncionario;
    @FXML private TableColumn<FuncionarioPedido, Integer> colIdPedido;
    @FXML private TextField txtIdFuncionario;
    @FXML private TextField txtIdPedido;

    private FuncionarioPedidoDAOImpl funcionarioPedidoDAO = new FuncionarioPedidoDAOImpl();
    private ObservableList<FuncionarioPedido> listaFuncionarioPedido;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colIdFuncionario.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getIdFuncionario()).asObject());
        colIdPedido.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getIdPedido()).asObject());

        carregarFuncionarioPedidos();
    }

    private void carregarFuncionarioPedidos() {
        listaFuncionarioPedido = FXCollections.observableArrayList(funcionarioPedidoDAO.readAll());
        tabelaFuncionarioPedido.setItems(listaFuncionarioPedido);
    }

    @FXML
    private void adicionarFuncionarioPedido() {
        try {
            int idFuncionario = Integer.parseInt(txtIdFuncionario.getText());
            int idPedido = Integer.parseInt(txtIdPedido.getText());
            FuncionarioPedido novoRegistro = new FuncionarioPedido(0, idFuncionario, idPedido);
            funcionarioPedidoDAO.create(novoRegistro);
            carregarFuncionarioPedidos();
            limparCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Os IDs devem ser números inteiros válidos!");
        }
    }

    @FXML
    private void atualizarFuncionarioPedido() {
        FuncionarioPedido selecionado = tabelaFuncionarioPedido.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta("Erro", "Selecione um registro para atualizar!");
            return;
        }
        try {
            selecionado.setIdFuncionario(Integer.parseInt(txtIdFuncionario.getText()));
            selecionado.setIdPedido(Integer.parseInt(txtIdPedido.getText()));
            funcionarioPedidoDAO.update(selecionado);
            carregarFuncionarioPedidos();
            limparCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Os IDs devem ser números inteiros válidos!");
        }
    }

    @FXML
    private void deletarFuncionarioPedido() {
        FuncionarioPedido selecionado = tabelaFuncionarioPedido.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta("Erro", "Selecione um registro para deletar!");
            return;
        }
        funcionarioPedidoDAO.delete(selecionado.getId());
        carregarFuncionarioPedidos();
    }

    private void limparCampos() {
        txtIdFuncionario.clear();
        txtIdPedido.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
