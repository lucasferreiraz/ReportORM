package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.Composicao;

public interface ComposicaoDao {
    
    void insert(Composicao obj);

    void update(Composicao obj);

    void deleteById(Integer id);

    Composicao findById(Integer id);

    List<Composicao> findAll();

}
