package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.LaudoDao;
import br.com.reportorm.entities.Laudo;

public class LaudoService implements LaudoDao {
    
    LaudoDao laudoDao = DaoFactory.createLaudoDao();

    @Override
    public void insert(Laudo obj) {
        laudoDao.insert(obj);

    }

    @Override
    public void update(Laudo obj) {
        laudoDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        laudoDao.deleteById(id);
        
    }

    @Override
    public Laudo findById(Integer id) {
        return laudoDao.findById(id);
    }

    @Override
    public List<Laudo> findAll() {
        return laudoDao.findAll();
    }

}
