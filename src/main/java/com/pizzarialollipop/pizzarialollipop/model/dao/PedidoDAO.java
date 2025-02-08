package com.pizzarialollipop.pizzarialollipop.model.dao;

import com.pizzarialollipop.pizzarialollipop.model.entities.Pedido;

import java.util.List;

public interface PedidoDAO {
    void create(Pedido pedido); // Adiciona um novo pedido
    Pedido read(int id); // Busca um pedido por ID
    List<Pedido> readAll(); // Busca todos os pedidos
    void update(Pedido pedido); // Atualiza um pedido existente
    void delete(int id); // Deleta um pedido pelo ID
}
