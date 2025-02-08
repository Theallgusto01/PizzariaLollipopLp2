package com.pizzarialollipop.pizzarialollipop.pizzaria.model;

import com.pizzarialollipop.pizzarialollipop.model.dao.impl.ProdutoDAOImpl;
import com.pizzarialollipop.pizzarialollipop.model.entities.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProdutoController {

    @FXML private TextField txtNome;
    @FXML private TextField txtPreco;
    @FXML private TableView<Produto> tabelaProdutos;
    @FXML private TableColumn<Produto, Integer> colId;
    @FXML private TableColumn<Produto, String> colNome;
    @FXML private TableColumn<Produto, Double> colPreco;

    private ProdutoDAOImpl produtoDAO = new ProdutoDAOImpl();
    private ObservableList<Produto> listaProdutos;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId_produto()).asObject());
        colNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome_produto()));
        colPreco.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getValor_produto()).asObject());

        carregarProdutos();
    }

    private void carregarProdutos() {
        listaProdutos = FXCollections.observableArrayList(produtoDAO.readAll());
        tabelaProdutos.setItems(listaProdutos);
    }

    @FXML
    private void adicionarProduto() {
        String nome = txtNome.getText();
        double preco;

        try {
            preco = Double.parseDouble(txtPreco.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "O preço deve ser um número válido!");
            return;
        }

        Produto novoProduto = new Produto(0, preco, nome);
        produtoDAO.create(novoProduto);
        carregarProdutos();
        limparCampos();
    }

    @FXML
    private void atualizarProduto() {
        Produto produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado == null) {
            mostrarAlerta("Erro", "Selecione um produto para atualizar!");
            return;
        }

        produtoSelecionado.setNome_produto(txtNome.getText());
        try {
            produtoSelecionado.setValor_produto(Double.parseDouble(txtPreco.getText()));
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "O preço deve ser um número válido!");
            return;
        }

        produtoDAO.update(produtoSelecionado);
        carregarProdutos();
        limparCampos();
    }

    @FXML
    private void deletarProduto() {
        Produto produtoSelecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado == null) {
            mostrarAlerta("Erro", "Selecione um produto para deletar!");
            return;
        }

        produtoDAO.delete(produtoSelecionado.getId_produto());
        carregarProdutos();
    }

    private void limparCampos() {
        txtNome.clear();
        txtPreco.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}