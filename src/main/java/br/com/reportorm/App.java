package br.com.reportorm;

import br.com.reportorm.database.DB;

public class App {
    public static void main( String[] args ){
        
        if(DB.getConnection() != null){
            System.out.println("Conexão com o banco de dados gerada com sucesso! ");
        } else {
            throw new RuntimeException("Ops! Erro ao conectar com o banco de dados. :(");
        }

        /* 
        CrudTest.insertions();
        System.out.println("Operações de inserção executadas com sucesso!");
        */

        /*
        CrudTest.reset();
        System.out.println("Tabelas resetadas!");
         */
    }

}
