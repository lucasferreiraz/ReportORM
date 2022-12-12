package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.MedicoDao;
import br.com.reportorm.entities.Medico;

public class MedicoService implements MedicoDao {
    
    MedicoDao medicoDao = DaoFactory.createMedicoDao();

    @Override
    public void insert(Medico obj) {
        medicoDao.insert(obj);

    }

    @Override
    public void update(Medico obj) {
        medicoDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        medicoDao.deleteById(id);
        
    }

    @Override
    public Medico findById(Integer id) {
        return medicoDao.findById(id);
    }

    @Override
    public List<Medico> findAll() {
        return medicoDao.findAll();
    }

}
