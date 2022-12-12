package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.ConsultaMedicaDao;
import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.entities.ConsultaMedica;

public class ConsultaMedicaService implements ConsultaMedicaDao {
    
    ConsultaMedicaDao consultaMedicaDao = DaoFactory.createConsultaMedicaDao();

    @Override
    public void insert(ConsultaMedica obj) {
        consultaMedicaDao.insert(obj);

    }

    @Override
    public void update(ConsultaMedica obj) {
        consultaMedicaDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        consultaMedicaDao.deleteById(id);
        
    }

    @Override
    public ConsultaMedica findById(Integer id) {
        return consultaMedicaDao.findById(id);
    }

    @Override
    public List<ConsultaMedica> findAll() {
        return consultaMedicaDao.findAll();
    }

}
