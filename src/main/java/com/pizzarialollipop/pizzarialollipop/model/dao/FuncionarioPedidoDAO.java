package com.pizzarialollipop.pizzarialollipop.model.dao;

import com.pizzarialollipop.pizzarialollipop.model.entities.FuncionarioPedido;
import java.util.List;

public interface FuncionarioPedidoDAO {
    void create(FuncionarioPedido funcionarioPedido);
    FuncionarioPedido read(int id);
    List<FuncionarioPedido> readAll();
    void delete(int id);
}
