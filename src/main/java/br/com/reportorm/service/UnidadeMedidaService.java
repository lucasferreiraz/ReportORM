package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.UnidadeMedidaDao;
import br.com.reportorm.entities.UnidadeMedida;

public class UnidadeMedidaService implements UnidadeMedidaDao {
    
    UnidadeMedidaDao unidadeMedidaDao = DaoFactory.createUnidadeMedidaDao();

    @Override
    public void insert(UnidadeMedida obj) {
        unidadeMedidaDao.insert(obj);

    }

    @Override
    public void update(UnidadeMedida obj) {
        unidadeMedidaDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        unidadeMedidaDao.deleteById(id);
        
    }

    @Override
    public UnidadeMedida findById(Integer id) {
        return unidadeMedidaDao.findById(id);
    }

    @Override
    public List<UnidadeMedida> findAll() {
        return unidadeMedidaDao.findAll();
    }

}
