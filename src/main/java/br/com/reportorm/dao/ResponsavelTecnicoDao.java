package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.ResponsavelTecnico;

public interface ResponsavelTecnicoDao {
    
    void insert(ResponsavelTecnico obj);

    void update(ResponsavelTecnico obj);

    void deleteById(Integer id);

    ResponsavelTecnico findById(Integer id);

    List<ResponsavelTecnico> findAll();

}
