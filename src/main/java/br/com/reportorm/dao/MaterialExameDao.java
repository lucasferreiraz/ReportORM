package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.MaterialExame;

public interface MaterialExameDao {
    
    void insert(MaterialExame obj);

    void update(MaterialExame obj);

    void deleteById(Integer id);

    MaterialExame findById(Integer id);

    List<MaterialExame> findAll();
}
