package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.Contato;
import br.com.reportorm.entities.Laboratorio;

public interface ContatoDao {
    
    void insert(Contato obj);

	void update(Contato obj);

	void deleteById(Integer id);

	Contato findById(Integer id);

	List<Contato> findAll();

	List<Contato> findByLaboratorio(Laboratorio laboratorio);
}
