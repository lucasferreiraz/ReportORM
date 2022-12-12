package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.ResponsavelTecnicoHasLaboratorio;

public interface ResponsavelTecnicoHasLaboratorioDao {
    
    void insert(ResponsavelTecnicoHasLaboratorio obj);

    void update(ResponsavelTecnicoHasLaboratorio obj);

    void deleteById(Integer id);

    ResponsavelTecnicoHasLaboratorio findById(Integer id);

    List<ResponsavelTecnicoHasLaboratorio> findAll();

}
