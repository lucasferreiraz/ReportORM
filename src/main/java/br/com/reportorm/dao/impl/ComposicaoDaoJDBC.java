package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.ComposicaoDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.Composicao;

public class ComposicaoDaoJDBC implements ComposicaoDao {

    private Connection conn;

    public ComposicaoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Composicao obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO composicao (exame_id,composicao_exame_id,valor_referencia_composicao_exame_id) VALUES (?,?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getExame().getId());
            st.setInt(2, obj.getComposicaoExame().getId());
            st.setInt(3, obj.getValorReferenciaComposicaoExame().getId());

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
    public void update(Composicao obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE composicao SET exame_id = ?, composicao_exame_id = ?, valor_referencia_composicao_exame_id = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getExame().getId());
            st.setInt(2, obj.getComposicaoExame().getId());
            st.setInt(3, obj.getValorReferenciaComposicaoExame().getId());

            st.setInt(4, obj.getId());

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
                "DELETE FROM composicao WHERE id = ?;"    
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
    public Composicao findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM composicao WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Composicao composicao = instantiateComposicao(rs);
                return composicao;
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
    public List<Composicao> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Composicao> composicaoList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM composicao;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                Composicao composicao = instantiateComposicao(rs);
                composicaoList.add(composicao);
            }

            return composicaoList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Composicao instantiateComposicao(ResultSet rs) throws SQLException {
        Composicao composicao = new Composicao();
        composicao.setId(rs.getInt("id"));
        composicao.setExameId(rs.getInt("exame_id"));
        composicao.setComposicaoExameId(rs.getInt("composicao_exame_id"));
        composicao.setValorReferenciaComposicaoExameId(rs.getInt("valor_referencia_composicao_exame_id"));

        return composicao;
    }
    
}
