package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.TipoExameDao;
import br.com.reportorm.entities.TipoExame;

public class TipoExameService implements TipoExameDao {
    
    TipoExameDao tipoExameDao = DaoFactory.createTipoExameDao();

    @Override
    public void insert(TipoExame obj) {
        tipoExameDao.insert(obj);

    }

    @Override
    public void update(TipoExame obj) {
        tipoExameDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        tipoExameDao.deleteById(id);
        
    }

    @Override
    public TipoExame findById(Integer id) {
        return tipoExameDao.findById(id);
    }

    @Override
    public List<TipoExame> findAll() {
        return tipoExameDao.findAll();
    }

}
