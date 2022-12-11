package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.ValorReferenciaComposicaoExameDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.ValorReferenciaComposicaoExame;

public class ValorReferenciaComposicaoExameDaoJDBC implements ValorReferenciaComposicaoExameDao {

    private Connection conn;

    public ValorReferenciaComposicaoExameDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ValorReferenciaComposicaoExame obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO valor_referencia_composicao_exame (valor_minimo,valor_maximo,limitador_minimo,limitador_maximo,unidade_medida_id) VALUES (?,?,?,?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getValorMinimo());
            st.setString(2, obj.getValorMaximo());
            st.setString(3, obj.getLimitadorMinimo());
            st.setString(4, obj.getLimitadorMaximo());
            st.setInt(5, obj.getUnidadeMedidaId());

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
    public void update(ValorReferenciaComposicaoExame obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE valor_referencia_composicao_exame SET valor_minimo = ?,valor_maximo = ?,limitador_minimo = ?,limitador_maximo = ?,unidade_medida_id = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getValorMinimo());
            st.setString(2, obj.getValorMaximo());
            st.setString(3, obj.getLimitadorMinimo());
            st.setString(4, obj.getLimitadorMaximo());
            st.setInt(5, obj.getUnidadeMedidaId());

            st.setInt(6, obj.getId());

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
                "DELETE FROM valor_referencia_composicao_exame WHERE id = ?;"    
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
    public ValorReferenciaComposicaoExame findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM valor_referencia_composicao_exame WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                ValorReferenciaComposicaoExame valorReferenciaComposicaoExame = instantiateValorReferenciaComposicaoExame(rs);
                return valorReferenciaComposicaoExame;
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
    public List<ValorReferenciaComposicaoExame> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<ValorReferenciaComposicaoExame> list = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM valor_referencia_composicao_exame;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                ValorReferenciaComposicaoExame valorReferenciaComposicaoExame = instantiateValorReferenciaComposicaoExame(rs);
                list.add(valorReferenciaComposicaoExame);
            }

            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private ValorReferenciaComposicaoExame instantiateValorReferenciaComposicaoExame(ResultSet rs) throws SQLException {
        ValorReferenciaComposicaoExame obj = new ValorReferenciaComposicaoExame();
        obj.setId(rs.getInt("id"));
        obj.setValorMinimo(rs.getString("valor_minimo"));
        obj.setValorMaximo(rs.getString("valor_maximo"));
        obj.setLimitadorMinimo(rs.getString("limitador_minimo"));
        obj.setLimitadorMaximo(rs.getString("limitador_maximo"));
        obj.setUnidadeMedidaId(rs.getInt("unidade_medida_id"));

        return obj;
    }
    
}
