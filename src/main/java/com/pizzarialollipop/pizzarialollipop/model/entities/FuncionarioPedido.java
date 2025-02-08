package com.pizzarialollipop.pizzarialollipop.model.entities;

public class FuncionarioPedido {
    private int id;
    private int idFuncionario;
    private int idPedido;

    public FuncionarioPedido() {}

    public FuncionarioPedido(int id, int idFuncionario, int idPedido) {
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.idPedido = idPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public String toString() {
        return "FuncionarioPedido{" +
                "id=" + id +
                ", idFuncionario=" + idFuncionario +
                ", idPedido=" + idPedido +
                '}';
    }
}
