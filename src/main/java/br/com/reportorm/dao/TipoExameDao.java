package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.TipoExame;

public interface TipoExameDao {
    
    void insert(TipoExame obj);

    void update(TipoExame obj);

    void deleteById(Integer id);

    TipoExame findById(Integer id);

    List<TipoExame> findAll();
}
