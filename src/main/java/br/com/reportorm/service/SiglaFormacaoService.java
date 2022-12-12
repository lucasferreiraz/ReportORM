package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.SiglaFormacaoDao;
import br.com.reportorm.entities.SiglaFormacao;

public class SiglaFormacaoService implements SiglaFormacaoDao {
    
    SiglaFormacaoDao siglaFormacaoDao = DaoFactory.createSiglaFormacaoDao();

    @Override
    public void insert(SiglaFormacao obj) {
        siglaFormacaoDao.insert(obj);

    }

    @Override
    public void update(SiglaFormacao obj) {
        siglaFormacaoDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        siglaFormacaoDao.deleteById(id);
        
    }

    @Override
    public SiglaFormacao findById(Integer id) {
        return siglaFormacaoDao.findById(id);
    }

    @Override
    public List<SiglaFormacao> findAll() {
        return siglaFormacaoDao.findAll();
    }

}
