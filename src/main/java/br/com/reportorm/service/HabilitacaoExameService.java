package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.HabilitacaoExameDao;
import br.com.reportorm.entities.HabilitacaoExame;

public class HabilitacaoExameService implements HabilitacaoExameDao {
    
    HabilitacaoExameDao habilitacaoExameDao = DaoFactory.createHabilitacaoExameDao();

    @Override
    public void insert(HabilitacaoExame obj) {
        habilitacaoExameDao.insert(obj);

    }

    @Override
    public void update(HabilitacaoExame obj) {
        habilitacaoExameDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        habilitacaoExameDao.deleteById(id);
        
    }

    @Override
    public HabilitacaoExame findById(Integer id) {
        return habilitacaoExameDao.findById(id);
    }

    @Override
    public List<HabilitacaoExame> findAll() {
        return habilitacaoExameDao.findAll();
    }

}
