package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.ComposicaoExame;

public interface ComposicaoExameDao {
    
    void insert(ComposicaoExame obj);

	void update(ComposicaoExame obj);

	void deleteById(Integer id);

	ComposicaoExame findById(Integer id);

	List<ComposicaoExame> findAll();

}
