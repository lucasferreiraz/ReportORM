package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.ValorReferenciaComposicaoExame;

public interface ValorReferenciaComposicaoExameDao {
    
    void insert(ValorReferenciaComposicaoExame obj);

    void update(ValorReferenciaComposicaoExame obj);

    void deleteById(Integer id);

    ValorReferenciaComposicaoExame findById(Integer id);

    List<ValorReferenciaComposicaoExame> findAll();

}
