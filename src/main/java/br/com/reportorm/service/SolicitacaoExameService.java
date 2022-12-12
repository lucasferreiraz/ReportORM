package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.SolicitacaoExameDao;
import br.com.reportorm.entities.SolicitacaoExame;

public class SolicitacaoExameService implements SolicitacaoExameDao {
    
    SolicitacaoExameDao solicitacaoExameDao = DaoFactory.createSolicitacaoExameDao();

    @Override
    public void insert(SolicitacaoExame obj) {
        solicitacaoExameDao.insert(obj);

    }

    @Override
    public void update(SolicitacaoExame obj) {
        solicitacaoExameDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        solicitacaoExameDao.deleteById(id);
        
    }

    @Override
    public SolicitacaoExame findById(Integer id) {
        return solicitacaoExameDao.findById(id);
    }

    @Override
    public List<SolicitacaoExame> findAll() {
        return solicitacaoExameDao.findAll();
    }

}
