package com.pizzarialollipop.pizzarialollipop.model.entities;

public class ProdutoPedido {
    private int id_produto;
    private int id_pedido;
    private int quantidade;

    // Construtor
    public ProdutoPedido(int id_produto, int id_pedido, int quantidade) {
        this.id_produto = id_produto;
        this.id_pedido = id_pedido;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ProdutoPedido{" +
                "id_produto=" + id_produto +
                ", id_pedido=" + id_pedido +
                ", quantidade=" + quantidade +
                '}';
    }
}
