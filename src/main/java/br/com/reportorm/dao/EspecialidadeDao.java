package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.Especialidade;

public interface EspecialidadeDao {
    
    void insert(Especialidade obj);

    void update(Especialidade obj);

    void deleteById(Integer id);

    Especialidade findById(Integer id);

    List<Especialidade> findAll();

}
