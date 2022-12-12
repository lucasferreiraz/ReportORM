package br.com.reportorm.service;

import java.util.List;

import br.com.reportorm.dao.DaoFactory;
import br.com.reportorm.dao.MedicoHasEspecialidadeDao;
import br.com.reportorm.entities.MedicoHasEspecialidade;

public class MedicoHasEspecialidadeService implements MedicoHasEspecialidadeDao {
    
    MedicoHasEspecialidadeDao medicoHasEspecialidadeDao = DaoFactory.createMedicoHasEspecialidadeDao();

    @Override
    public void insert(MedicoHasEspecialidade obj) {
        medicoHasEspecialidadeDao.insert(obj);

    }

    @Override
    public void update(MedicoHasEspecialidade obj) {
        medicoHasEspecialidadeDao.update(obj);
        
    }

    @Override
    public void deleteById(Integer id) {
        medicoHasEspecialidadeDao.deleteById(id);
        
    }

    @Override
    public MedicoHasEspecialidade findById(Integer id) {
        return medicoHasEspecialidadeDao.findById(id);
    }

    @Override
    public List<MedicoHasEspecialidade> findAll() {
        return medicoHasEspecialidadeDao.findAll();
    }

}
