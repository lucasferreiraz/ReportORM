package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.LaboratorioDao;
import br.com.reportorm.entities.Laboratorio;

public class LaboratorioService implements LaboratorioDao {
    
    LaboratorioDao laboratorioDao = DaoFactory.createLaboratorioDao();

    @Override
    public void insert(Laboratorio obj) {
        laboratorioDao.insert(obj);

    }

    @Override
    public void update(Laboratorio obj) {
        laboratorioDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        laboratorioDao.deleteById(id);
        
    }

    @Override
    public Laboratorio findById(Integer id) {
        return laboratorioDao.findById(id);
    }

    @Override
    public List<Laboratorio> findAll() {
        return laboratorioDao.findAll();
    }

}
