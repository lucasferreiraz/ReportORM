package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.ResultadoExameDao;
import br.com.reportorm.entities.ResultadoExame;

public class ResultadoExameService implements ResultadoExameDao {
    
    ResultadoExameDao resultadoExameDao = DaoFactory.createResultadoExameDao();

    @Override
    public void insert(ResultadoExame obj) {
        resultadoExameDao.insert(obj);

    }

    @Override
    public void update(ResultadoExame obj) {
        resultadoExameDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        resultadoExameDao.deleteById(id);
        
    }

    @Override
    public ResultadoExame findById(Integer id) {
        return resultadoExameDao.findById(id);
    }

    @Override
    public List<ResultadoExame> findAll() {
        return resultadoExameDao.findAll();
    }

}
