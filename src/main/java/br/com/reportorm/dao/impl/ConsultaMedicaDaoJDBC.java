package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.ConsultaMedicaDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.ConsultaMedica;

public class ConsultaMedicaDaoJDBC implements ConsultaMedicaDao {

    private Connection conn;

    public ConsultaMedicaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ConsultaMedica obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO consulta_medica (dt_consulta,medico_id,paciente_id,nm_atendimento) VALUES (?,?,?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setDate(1, new Date(obj.getDtConsulta().getTime()));
            st.setInt(2, obj.getMedico().getId());
            st.setInt(3, obj.getPaciente().getId());
            st.setString(4, obj.getNmAtendimento());

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
    public void update(ConsultaMedica obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE consulta_medica SET dt_consulta = ?,medico_id = ?,paciente_id = ?,nm_atendimento = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setDate(1, new Date(obj.getDtConsulta().getTime()));
            st.setInt(2, obj.getMedico().getId());
            st.setInt(3, obj.getPaciente().getId());
            st.setString(4, obj.getNmAtendimento());

            st.setInt(5, obj.getId());

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
                "DELETE FROM consulta_medica WHERE id = ?;"    
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
    public ConsultaMedica findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM consulta_medica WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                ConsultaMedica consultaMedica = instantiateConsultaMedica(rs);
                return consultaMedica;
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
    public List<ConsultaMedica> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<ConsultaMedica> consultaMedicaList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM consulta_medica;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                ConsultaMedica consultaMedica = instantiateConsultaMedica(rs);
                consultaMedicaList.add(consultaMedica);
            }

            return consultaMedicaList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private ConsultaMedica instantiateConsultaMedica(ResultSet rs) throws SQLException {
        ConsultaMedica consultaMedica = new ConsultaMedica();
        consultaMedica.setId(rs.getInt("id"));
        consultaMedica.setDtConsulta(rs.getDate("dt_consulta"));
        consultaMedica.setNmAtendimento(rs.getString("nm_atendimento"));
        consultaMedica.setMedicoId(rs.getInt("medico_id"));
        consultaMedica.setPacienteId(rs.getInt("paciente_id"));

        return consultaMedica;
    }
    
}
