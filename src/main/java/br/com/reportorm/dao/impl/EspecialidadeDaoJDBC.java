package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.EspecialidadeDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.Especialidade;

public class EspecialidadeDaoJDBC implements EspecialidadeDao {

    private Connection conn;

    public EspecialidadeDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Especialidade obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO especialidade (descricao,observacao) VALUES (?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getDescricao());
            st.setString(2, obj.getObservacao());

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
    public void update(Especialidade obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE especialidade SET descricao = ?,observacao = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getDescricao());
            st.setString(2, obj.getObservacao());
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
                "DELETE FROM especialidade WHERE id = ?;"    
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
    public Especialidade findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM especialidade WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Especialidade especialidade = instantiateEspecialidade(rs);
                return especialidade;
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
    public List<Especialidade> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Especialidade> especialidadeList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM especialidade;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                Especialidade especialidade = instantiateEspecialidade(rs);
                especialidadeList.add(especialidade);
            }

            return especialidadeList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Especialidade instantiateEspecialidade(ResultSet rs) throws SQLException {
        Especialidade especialidade = new Especialidade();
        especialidade.setId(rs.getInt("id"));
        especialidade.setDescricao(rs.getString("descricao"));
        especialidade.setObservacao(rs.getString("observacao"));

        return especialidade;
    }
    
}
