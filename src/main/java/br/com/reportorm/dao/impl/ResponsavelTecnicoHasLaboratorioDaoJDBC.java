package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.ResponsavelTecnicoHasLaboratorioDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.ResponsavelTecnicoHasLaboratorio;

public class ResponsavelTecnicoHasLaboratorioDaoJDBC implements ResponsavelTecnicoHasLaboratorioDao {

    private Connection conn;

    public ResponsavelTecnicoHasLaboratorioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ResponsavelTecnicoHasLaboratorio obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO responsavel_tecnico_has_laboratorio (responsavel_tecnico_id,laboratorio_id) VALUES (?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getResponsavelTecnicoId());
            st.setInt(2, obj.getLaboratorioId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
       } catch (SQLException e) {
            throw new DbException(e.getMessage());
       } finally {
            DB.closeStatement(st);
       }
    }

    @Override
    public void update(ResponsavelTecnicoHasLaboratorio obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE responsavel_tecnico_has_laboratorio SET responsavel_tecnico_id = ?,laboratorio_id = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getResponsavelTecnicoId());
            st.setInt(2, obj.getLaboratorioId());

            st.setInt(8, obj.getId());

            st.executeUpdate();

       } catch (SQLException e) {
            throw new DbException(e.getMessage());
       } finally {
            DB.closeStatement(st);
       }
        
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "DELETE FROM responsavel_tecnico_has_laboratorio WHERE id = ?;"    
            );

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
        
    }

    @Override
    public ResponsavelTecnicoHasLaboratorio findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM responsavel_tecnico_has_laboratorio WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                ResponsavelTecnicoHasLaboratorio obj = instantiateResponsavelTecnicoHasLaboratorio(rs);
                return obj;
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<ResponsavelTecnicoHasLaboratorio> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<ResponsavelTecnicoHasLaboratorio> list = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM responsavel_tecnico_has_laboratorio;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                ResponsavelTecnicoHasLaboratorio obj = instantiateResponsavelTecnicoHasLaboratorio(rs);
                list.add(obj);
            }

            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private ResponsavelTecnicoHasLaboratorio instantiateResponsavelTecnicoHasLaboratorio(ResultSet rs) throws SQLException {
        ResponsavelTecnicoHasLaboratorio obj = new ResponsavelTecnicoHasLaboratorio();
        obj.setId(rs.getInt("id"));
        obj.setResponsavelTecnicoId(rs.getInt("reponsavel_tecnico_id"));
        obj.setLaboratorioId(rs.getInt("laboratorio_id"));

        return obj;
    }
    
}
