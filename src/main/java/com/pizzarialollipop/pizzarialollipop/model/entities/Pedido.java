package com.pizzarialollipop.pizzarialollipop.model.entities;

import java.time.LocalDateTime;

public class Pedido {
    private int id_pedido;
    private double valor_total;
    private String forma_pagamento;
    private double troco;
    private LocalDateTime data_hora;
    private int id_cliente;

    // Construtor
    public Pedido(int id_pedido, double valor_total, String forma_pagamento, double troco, LocalDateTime data_hora, int id_cliente) {
        this.id_pedido = id_pedido;
        this.valor_total = valor_total;
        this.forma_pagamento = forma_pagamento;
        this.troco = troco;
        this.data_hora = data_hora;
        this.id_cliente = id_cliente;
    }

    // Construtor sem o id_pedido (usado para criação de novos pedidos)
    public Pedido(double valor_total, String forma_pagamento, double troco, LocalDateTime data_hora, int id_cliente) {
        this.valor_total = valor_total;
        this.forma_pagamento = forma_pagamento;
        this.troco = troco;
        this.data_hora = data_hora;
        this.id_cliente = id_cliente;
    }

    // Getters e Setters
    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id_pedido=" + id_pedido +
                ", valor_total=" + valor_total +
                ", forma_pagamento='" + forma_pagamento + '\'' +
                ", troco=" + troco +
                ", data_hora=" + data_hora +
                ", id_cliente=" + id_cliente +
                '}';
    }
}
