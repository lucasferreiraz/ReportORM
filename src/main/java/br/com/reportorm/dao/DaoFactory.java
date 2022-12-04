package br.com.reportorm.dao;

import br.com.reportorm.dao.impl.LaboratorioDaoJDBC;
import br.com.reportorm.database.DB;

public class DaoFactory {
    
    public static LaboratorioDao createLaboratorioDao(){
        return new LaboratorioDaoJDBC(DB.getConnection());
    }

}
