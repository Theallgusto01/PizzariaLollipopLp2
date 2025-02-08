package com.pizzarialollipop.pizzarialollipop.model.dao;

import com.pizzarialollipop.pizzarialollipop.model.entities.ProdutoPedido;

import java.util.List;

public interface ProdutoPedidoDAO {
    void create(ProdutoPedido produtoPedido); // Adiciona um novo produto ao pedido
    ProdutoPedido read(int id_produto, int id_pedido); // Busca um produto no pedido pelo ID do produto e ID do pedido
    List<ProdutoPedido> readAll(); // Busca todos os produtos de todos os pedidos
    void update(ProdutoPedido produtoPedido); // Atualiza a quantidade de um produto no pedido
    void delete(int id_produto, int id_pedido); // Deleta um produto do pedido pelo ID do produto e ID do pedido
}
