package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.UnidadeMedidaDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.UnidadeMedida;

public class UnidadeMedidaDaoJDBC implements UnidadeMedidaDao {

    private Connection conn;

    public UnidadeMedidaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(UnidadeMedida obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO unidade_medida (descricao) VALUES (?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getDescricao());

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
    public void update(UnidadeMedida obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE unidade_medida SET descricao = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getDescricao());
            st.setInt(2, obj.getId());

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
                "DELETE FROM unidade_medida WHERE id = ?;"    
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
    public UnidadeMedida findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM unidade_medida WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                UnidadeMedida unidadeMedida = instantiateUnidadeMedida(rs);
                return unidadeMedida;
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
    public List<UnidadeMedida> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<UnidadeMedida> unidadeMedidaList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM unidade_medida;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                UnidadeMedida unidadeMedida = instantiateUnidadeMedida(rs);
                unidadeMedidaList.add(unidadeMedida);
            }

            return unidadeMedidaList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private UnidadeMedida instantiateUnidadeMedida(ResultSet rs) throws SQLException {
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setId(rs.getInt("id"));
        unidadeMedida.setDescricao(rs.getString("descricao"));

        return unidadeMedida;
    }
    
}
