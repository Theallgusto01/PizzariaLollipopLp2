package com.pizzarialollipop.pizzarialollipop.pizzaria.model;

public class Pizza {
    private int id_produto;
    private String nome_produto;
    private double valor_produto;

    public Pizza(int id, String name, double price) {
        this.id_produto = id;
        this.nome_produto = name;
        this.valor_produto = price;
    }

    public int getId() {
        return id_produto;
    }

    public String getName() {
        return nome_produto;
    }

    public double getPrice() {
        return valor_produto;
    }

    public void setName(String name) {
        this.nome_produto = name;
    }

    public void setPrice(double price) {
        this.valor_produto = price;
    }


//    @Override
//    public String toString() {
//        return "Pizza [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
//    }
}
