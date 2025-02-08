package com.pizzarialollipop.pizzarialollipop.model.dao;

import com.pizzarialollipop.pizzarialollipop.model.entities.Cliente;
import java.util.List;

public interface ClienteDAO {
    void create(Cliente cliente);
    Cliente read(int id);
    List<Cliente> readAll();
    void update(Cliente cliente);
    void delete(int id);
}
