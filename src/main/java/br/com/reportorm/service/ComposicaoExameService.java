package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.ComposicaoExameDao;
import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.entities.ComposicaoExame;

public class ComposicaoExameService implements ComposicaoExameDao {
    
    ComposicaoExameDao composicaoExameDao = DaoFactory.createComposicaoExameDao();

    @Override
    public void insert(ComposicaoExame obj) {
        composicaoExameDao.insert(obj);

    }

    @Override
    public void update(ComposicaoExame obj) {
        composicaoExameDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        composicaoExameDao.deleteById(id);
        
    }

    @Override
    public ComposicaoExame findById(Integer id) {
        return composicaoExameDao.findById(id);
    }

    @Override
    public List<ComposicaoExame> findAll() {
        return composicaoExameDao.findAll();
    }

}
