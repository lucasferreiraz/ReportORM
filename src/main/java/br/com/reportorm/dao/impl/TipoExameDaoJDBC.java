package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.TipoExameDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.TipoExame;

public class TipoExameDaoJDBC implements TipoExameDao {

    private Connection conn;

    public TipoExameDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(TipoExame obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO tipo_exame (descricao,observacao) VALUES (?,?);",
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
    public void update(TipoExame obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE tipo_exame SET descricao = ?,observacao = ? WHERE id = ?;",
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
                "DELETE FROM tipo_exame WHERE id = ?;"    
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
    public TipoExame findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM especialidade WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                TipoExame tipoExame = instantiateTipoExame(rs);
                return tipoExame;
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
    public List<TipoExame> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<TipoExame> tipoExameList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM laboratorio;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                TipoExame tipoExame = instantiateTipoExame(rs);
                tipoExameList.add(tipoExame);
            }

            return tipoExameList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private TipoExame instantiateTipoExame(ResultSet rs) throws SQLException {
        TipoExame tipoExame = new TipoExame();
        tipoExame.setId(rs.getInt("id"));
        tipoExame.setDescricao(rs.getString("descricao"));
        tipoExame.setObservacao(rs.getString("observacao"));

        return tipoExame;
    }
    
}
