package com.pizzarialollipop.pizzarialollipop.model.dao;

import com.pizzarialollipop.pizzarialollipop.model.entities.Funcionario;
import java.util.List;

public interface FuncionarioDAO {
    void create(Funcionario funcionario);
    Funcionario read(long id);
    List<Funcionario> readAll();
    void update(Funcionario funcionario);
    void delete(long id);
}
