package com.pizzarialollipop.pizzarialollipop.model.entities;

public class Produto {

    private int id_produto;
    private String nome_produto;
    private double valor_produto;

    public Produto(int id_produto, double valor_produto, String nome_produto) {
        this.id_produto = id_produto;
        this.valor_produto = valor_produto;
        this.nome_produto = nome_produto;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public double getValor_produto() {
        return valor_produto;
    }

    public void setValor_produto(double valor_produto) {
        this.valor_produto = valor_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }
}
