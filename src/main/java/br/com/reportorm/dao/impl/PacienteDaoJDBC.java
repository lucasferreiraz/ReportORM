package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.PacienteDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.Paciente;

public class PacienteDaoJDBC implements PacienteDao {

    private Connection conn;

    public PacienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Paciente obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO paciente (nome,dt_nascimento) VALUES (?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setDate(2, new Date(obj.getDt_nascimento().getTime()));

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
    public void update(Paciente obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE paciente SET nome = ?,dt_nascimento = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setDate(2, new Date(obj.getDt_nascimento().getTime()));
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
                "DELETE FROM paciente WHERE id = ?;"    
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
    public Paciente findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM paciente WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Paciente paciente = instantiatePaciente(rs);
                return paciente;
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
    public List<Paciente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Paciente> pacienteList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM paciente;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                Paciente paciente = instantiatePaciente(rs);
                pacienteList.add(paciente);
            }

            return pacienteList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Paciente instantiatePaciente(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setId(rs.getInt("id"));
        paciente.setNome(rs.getString("nome"));
        paciente.setDt_nascimento(rs.getDate("dt_nascimento"));
        
        return paciente;
    }
    
}
