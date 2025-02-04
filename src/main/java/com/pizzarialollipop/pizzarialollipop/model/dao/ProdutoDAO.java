package com.pizzarialollipop.pizzarialollipop.model.dao;

import com.pizzarialollipop.pizzarialollipop.pizzaria.model.Pizza;
import java.util.List;

public interface ProdutoDAO {
    void create(Pizza pizza);
    Pizza read(int id);
    List<Pizza> readAll();
    void update(Pizza pizza);
    void delete(int id);
}
