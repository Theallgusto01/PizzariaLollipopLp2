package com.pizzarialollipop.pizzarialollipop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CardapioController {

    @FXML
    private ListView<String> listViewProdutos;

    @FXML
    public void initialize() {
        // Adicionando produtos à lista
        listViewProdutos.getItems().addAll(
                "MUSSARELA  -  R$24   (ativo) \n(mussarela, oregano)",
                "CALABRESA  -  R$44   (ativo) \n(calabresa, mussarela, oregano)",
                "4 QUEIJOS  -  R$45   (ativo) \n(cheddar, requeijão, catupiry, mussarela, oregano)",
                "FRANGO  -  R$28   (ativo) \n(frango, mussarela, oregano)",
                "CARNE DE SOL  -  R$29   (ativo) \n(carne de sol, mussarela, oregano)",
                "PEPPERONI  -  R$30   (ativo) \n(pepperoni, mussarela, oregano)"
        );
    }

    @FXML
    public void cadastrarProduto() {
        System.out.println("Cadastrar produto...");
    }

    @FXML
    public void editarProduto() {
        System.out.println("Editar produto...");
    }

    @FXML
    public void removerProduto() {
        System.out.println("Remover produto...");
    }

    @FXML
    public void listarProdutos() {
        System.out.println("Listar produtos...");
    }
}
