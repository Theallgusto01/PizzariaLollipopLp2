package com.pizzarialollipop.pizzarialollipop.pizzaria.model;

import com.pizzarialollipop.pizzarialollipop.model.dao.impl.PedidoDAOImpl;
import com.pizzarialollipop.pizzarialollipop.model.entities.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;

public class PedidoController {

    @FXML private TextField txtValorTotal;
    @FXML private TextField txtFormaPagamento;
    @FXML private TextField txtTroco;
    @FXML private TextField txtIdCliente;
    @FXML private TableView<Pedido> tabelaPedidos;
    @FXML private TableColumn<Pedido, Integer> colId;
    @FXML private TableColumn<Pedido, Double> colValorTotal;
    @FXML private TableColumn<Pedido, String> colFormaPagamento;
    @FXML private TableColumn<Pedido, Double> colTroco;
    @FXML private TableColumn<Pedido, LocalDateTime> colDataHora;
    @FXML private TableColumn<Pedido, Integer> colIdCliente;

    private PedidoDAOImpl pedidoDAO = new PedidoDAOImpl();
    private ObservableList<Pedido> listaPedidos;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId_pedido()).asObject());
        colValorTotal.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getValor_total()).asObject());
        colFormaPagamento.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getForma_pagamento()));
        colTroco.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getTroco()).asObject());
        colDataHora.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getData_hora()));
        colIdCliente.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId_cliente()).asObject());

        carregarPedidos();
    }

    private void carregarPedidos() {
        listaPedidos = FXCollections.observableArrayList(pedidoDAO.readAll());
        tabelaPedidos.setItems(listaPedidos);
    }

    @FXML
    private void adicionarPedido() {
        try {
            double valorTotal = Double.parseDouble(txtValorTotal.getText());
            String formaPagamento = txtFormaPagamento.getText();
            double troco = Double.parseDouble(txtTroco.getText());
            int idCliente = Integer.parseInt(txtIdCliente.getText());

            Pedido novoPedido = new Pedido(valorTotal, formaPagamento, troco, LocalDateTime.now(), idCliente);
            pedidoDAO.create(novoPedido);
            carregarPedidos();
            limparCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Certifique-se de inserir valores numéricos válidos!");
        }
    }

    @FXML
    private void atualizarPedido() {
        Pedido pedidoSelecionado = tabelaPedidos.getSelectionModel().getSelectedItem();
        if (pedidoSelecionado == null) {
            mostrarAlerta("Erro", "Selecione um pedido para atualizar!");
            return;
        }

        try {
            pedidoSelecionado.setValor_total(Double.parseDouble(txtValorTotal.getText()));
            pedidoSelecionado.setForma_pagamento(txtFormaPagamento.getText());
            pedidoSelecionado.setTroco(Double.parseDouble(txtTroco.getText()));
            pedidoSelecionado.setId_cliente(Integer.parseInt(txtIdCliente.getText()));

            pedidoDAO.update(pedidoSelecionado);
            carregarPedidos();
            limparCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Certifique-se de inserir valores numéricos válidos!");
        }
    }

    @FXML
    private void deletarPedido() {
        Pedido pedidoSelecionado = tabelaPedidos.getSelectionModel().getSelectedItem();
        if (pedidoSelecionado == null) {
            mostrarAlerta("Erro", "Selecione um pedido para deletar!");
            return;
        }

        pedidoDAO.delete(pedidoSelecionado.getId_pedido());
        carregarPedidos();
    }

    private void limparCampos() {
        txtValorTotal.clear();
        txtFormaPagamento.clear();
        txtTroco.clear();
        txtIdCliente.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
