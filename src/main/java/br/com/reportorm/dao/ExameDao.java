package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.Exame;

public interface ExameDao {
    
    void insert(Exame obj);

	void update(Exame obj);

	void deleteById(Integer id);

	Exame findById(Integer id);

	List<Exame> findAll();

}
