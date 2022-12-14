package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.MedicoHasEspecialidadeDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.MedicoHasEspecialidade;

public class MedicoHasEspecialidadeDaoJDBC implements MedicoHasEspecialidadeDao {

    private Connection conn;

    public MedicoHasEspecialidadeDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(MedicoHasEspecialidade obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO medico_has_especialidade (medico_id,especialidade_id) VALUES (?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getMedico().getId());
            st.setInt(2, obj.getEspecialidade().getId());

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
    public void update(MedicoHasEspecialidade obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE medico_has_especialidade SET medico_id = ?,especialidade_id = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getMedico().getId());
            st.setInt(2, obj.getEspecialidade().getId());

            st.setInt(3, obj.getId());

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
                "DELETE FROM medico_has_especialidade WHERE id = ?;"    
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
    public MedicoHasEspecialidade findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM medico_has_especialidade WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                MedicoHasEspecialidade mEspecialidade = instantiateMedicoHasEspecialidade(rs);
                return mEspecialidade;
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
    public List<MedicoHasEspecialidade> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<MedicoHasEspecialidade> medicoHasEspecialidadeList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM medico_has_especialidade;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                MedicoHasEspecialidade medicoHasEspecialidade = instantiateMedicoHasEspecialidade(rs);
                medicoHasEspecialidadeList.add(medicoHasEspecialidade);
            }

            return medicoHasEspecialidadeList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private MedicoHasEspecialidade instantiateMedicoHasEspecialidade(ResultSet rs) throws SQLException {
        MedicoHasEspecialidade medicoHasEspecialidade = new MedicoHasEspecialidade();
        medicoHasEspecialidade.setId(rs.getInt("id"));
        medicoHasEspecialidade.setMedicoId(rs.getInt("medico_id"));
        medicoHasEspecialidade.setEspecialidadeId(rs.getInt("medico_id"));

        return medicoHasEspecialidade;
    }
    
}
