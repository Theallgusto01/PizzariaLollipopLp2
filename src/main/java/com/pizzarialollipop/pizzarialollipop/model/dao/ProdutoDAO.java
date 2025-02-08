package com.pizzarialollipop.pizzarialollipop.model.dao;

import com.pizzarialollipop.pizzarialollipop.model.entities.Produto;
import java.util.List;

public interface ProdutoDAO {
    void create(Produto produto);
    Produto read(int id);
    List<Produto> readAll();
    void update(Produto produto);
    void delete(int id);
}
