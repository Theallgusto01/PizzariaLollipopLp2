package com.pizzarialollipop.pizzarialollipop.pizzaria.model;

import com.pizzarialollipop.pizzarialollipop.model.dao.impl.ProdutoPedidoDAOImpl;
import com.pizzarialollipop.pizzarialollipop.model.entities.ProdutoPedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProdutoPedidoController {

    @FXML private TextField txtIdProduto;
    @FXML private TextField txtIdPedido;
    @FXML private TextField txtQuantidade;
    @FXML private TableView<ProdutoPedido> tabelaProdutoPedido;
    @FXML private TableColumn<ProdutoPedido, Integer> colIdProduto;
    @FXML private TableColumn<ProdutoPedido, Integer> colIdPedido;
    @FXML private TableColumn<ProdutoPedido, Integer> colQuantidade;

    private ProdutoPedidoDAOImpl produtoPedidoDAO = new ProdutoPedidoDAOImpl();
    private ObservableList<ProdutoPedido> listaProdutoPedido;

    @FXML
    public void initialize() {
        colIdProduto.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId_produto()).asObject());
        colIdPedido.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId_pedido()).asObject());
        colQuantidade.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getQuantidade()).asObject());

        carregarProdutoPedido();
    }

    private void carregarProdutoPedido() {
        listaProdutoPedido = FXCollections.observableArrayList(produtoPedidoDAO.readAll());
        tabelaProdutoPedido.setItems(listaProdutoPedido);
    }

    @FXML
    private void adicionarProdutoPedido() {
        try {
            int idProduto = Integer.parseInt(txtIdProduto.getText());
            int idPedido = Integer.parseInt(txtIdPedido.getText());
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            ProdutoPedido novoProdutoPedido = new ProdutoPedido(idProduto, idPedido, quantidade);
            produtoPedidoDAO.create(novoProdutoPedido);
            carregarProdutoPedido();
            limparCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Os valores devem ser números válidos!");
        }
    }

    @FXML
    private void atualizarProdutoPedido() {
        ProdutoPedido selecionado = tabelaProdutoPedido.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta("Erro", "Selecione um item para atualizar!");
            return;
        }

        try {
            selecionado.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            produtoPedidoDAO.update(selecionado);
            carregarProdutoPedido();
            limparCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "A quantidade deve ser um número válido!");
        }
    }

    @FXML
    private void deletarProdutoPedido() {
        ProdutoPedido selecionado = tabelaProdutoPedido.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta("Erro", "Selecione um item para deletar!");
            return;
        }

        produtoPedidoDAO.delete(selecionado.getId_produto(), selecionado.getId_pedido());
        carregarProdutoPedido();
    }

    private void limparCampos() {
        txtIdProduto.clear();
        txtIdPedido.clear();
        txtQuantidade.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
