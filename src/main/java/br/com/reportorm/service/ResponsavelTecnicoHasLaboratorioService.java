package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.ResponsavelTecnicoHasLaboratorioDao;
import br.com.reportorm.entities.ResponsavelTecnicoHasLaboratorio;

public class ResponsavelTecnicoHasLaboratorioService implements ResponsavelTecnicoHasLaboratorioDao {
    
    ResponsavelTecnicoHasLaboratorioDao responsavelTecnicoDao = DaoFactory.createResponsavelTecnicoHasLaboratorioDao();

    @Override
    public void insert(ResponsavelTecnicoHasLaboratorio obj) {
        responsavelTecnicoDao.insert(obj);

    }

    @Override
    public void update(ResponsavelTecnicoHasLaboratorio obj) {
        responsavelTecnicoDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        responsavelTecnicoDao.deleteById(id);
        
    }

    @Override
    public ResponsavelTecnicoHasLaboratorio findById(Integer id) {
        return responsavelTecnicoDao.findById(id);
    }

    @Override
    public List<ResponsavelTecnicoHasLaboratorio> findAll() {
        return responsavelTecnicoDao.findAll();
    }

}
