package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.ResponsavelTecnicoDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.ResponsavelTecnico;

public class ResponsavelTecnicoDaoJDBC implements ResponsavelTecnicoDao {

    private Connection conn;

    public ResponsavelTecnicoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ResponsavelTecnico obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO responsavel_tecnico (nome,conselho,formacao,sigla_formacao_id) VALUES (?,?,?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getConselho());
            st.setString(3, obj.getFormacao());
            st.setInt(4, obj.getSiglaFormacaoId());

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
    public void update(ResponsavelTecnico obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE responsavel_tecnico SET nome = ?,conselho = ?,formacao = ?,sigla_formacao_id = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getConselho());
            st.setString(3, obj.getFormacao());
            st.setInt(4, obj.getSiglaFormacaoId());

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
                "DELETE FROM responsavel_tecnico WHERE id = ?;"    
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
    public ResponsavelTecnico findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM responsavel_tecnico WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                ResponsavelTecnico responsavelTecnico = instantiateResponsavelTecnico(rs);
                return responsavelTecnico;
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
    public List<ResponsavelTecnico> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<ResponsavelTecnico> responsavelTecnicoList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM responsavel_tecnico;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                ResponsavelTecnico responsavelTecnico = instantiateResponsavelTecnico(rs);
                responsavelTecnicoList.add(responsavelTecnico);
            }

            return responsavelTecnicoList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private ResponsavelTecnico instantiateResponsavelTecnico(ResultSet rs) throws SQLException {
        ResponsavelTecnico responsavelTecnico = new ResponsavelTecnico();
        responsavelTecnico.setId(rs.getInt("id"));
        responsavelTecnico.setNome(rs.getString("nome"));
        responsavelTecnico.setConselho(rs.getString("conselho"));
        responsavelTecnico.setFormacao(rs.getString("formacao"));
        responsavelTecnico.setSiglaFormacaoId(rs.getInt("sigla_formacao_id"));

        return responsavelTecnico;
    }
    
}
