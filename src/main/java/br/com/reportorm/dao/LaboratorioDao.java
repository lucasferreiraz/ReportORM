package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.Laboratorio;

public interface LaboratorioDao {
    
    void insert(Laboratorio obj);

    void update(Laboratorio obj);

    void deleteById(Integer id);

    Laboratorio findById(Integer id);

    List<Laboratorio> findAll();

}
