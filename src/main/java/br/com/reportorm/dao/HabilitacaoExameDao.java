package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.HabilitacaoExame;

public interface HabilitacaoExameDao {
    
    void insert(HabilitacaoExame obj);

    void update(HabilitacaoExame obj);

    void deleteById(Integer id);

    HabilitacaoExame findById(Integer id);

    List<HabilitacaoExame> findAll();

}
