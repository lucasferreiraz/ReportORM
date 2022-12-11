package br.com.reportorm.dao;

import java.util.List;

import br.com.reportorm.entities.ConsultaMedica;

public interface ConsultaMedicaDao {
    
    void insert(ConsultaMedica obj);

    void update(ConsultaMedica obj);

    void deleteById(Integer id);

    ConsultaMedica findById(Integer id);

    List<ConsultaMedica> findAll();

}
