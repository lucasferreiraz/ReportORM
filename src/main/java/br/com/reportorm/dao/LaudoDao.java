package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.Laudo;

public interface LaudoDao {
    
    void insert(Laudo obj);

    void update(Laudo obj);

    void deleteById(Integer id);

    Laudo findById(Integer id);

    List<Laudo> findAll();

}
