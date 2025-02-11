package com.pizzarialollipop.pizzarialollipop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationController {

    private static Stage stage;


    @FXML
    void onProcurarProdutoClick(){
        try {
            stage = com.pizzarialollipop.pizzarialollipop.Application.newStage("produto-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onProcurarClienteClick() {
        System.out.println("Procurar Cliente clicado");
        // Adicione lógica para buscar cliente
    }

    @FXML
    private void onListarClienteClick() {
        System.out.println("Listar Todos os Clientes clicado");
        // Adicione lógica para listar clientes
    }

    @FXML
    private void onCadastrarPedidoClick() {
        System.out.println("Cadastrar Pedido clicado");
        // Adicione lógica para abrir tela de cadastro de pedido
    }

    @FXML
    private void onProcurarPedidoClick() {
        System.out.println("Procurar Pedido clicado");
        // Adicione lógica para buscar pedido
    }

    @FXML
    private void onListarPedidoClick() {
        System.out.println("Listar Todos os Pedidos clicado");
        // Adicione lógica para listar pedidos
    }

    @FXML
    private void onCadastrarProdutoClick() {
        System.out.println("Cadastrar Produto clicado");
        // Adicione lógica para abrir tela de cadastro de produto
    }
    @FXML
    private void onListarProdutoClick() {
        System.out.println("Listar Todos os Produtos clicado");
        // Adicione lógica para listar produtos
    }
}
