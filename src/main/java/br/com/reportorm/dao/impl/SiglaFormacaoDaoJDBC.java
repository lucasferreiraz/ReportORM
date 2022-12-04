package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.SiglaFormacaoDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.SiglaFormacao;

public class SiglaFormacaoDaoJDBC implements SiglaFormacaoDao {

    private Connection conn;

    public SiglaFormacaoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(SiglaFormacao obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO sigla_formacao (sigla) VALUES (?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getSigla());

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
    public void update(SiglaFormacao obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE sigla_formacao SET sigla = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getSigla());
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
                "DELETE FROM sigla_formacao WHERE id = ?;"    
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
    public SiglaFormacao findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM sigla_formacao WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                SiglaFormacao siglaFormacao = instantiateSiglaFormacao(rs);
                return siglaFormacao;
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
    public List<SiglaFormacao> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<SiglaFormacao> siglaFormacoes = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM sigla_formacao;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                SiglaFormacao siglaFormacao = instantiateSiglaFormacao(rs);
                siglaFormacoes.add(siglaFormacao);
            }

            return siglaFormacoes;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private SiglaFormacao instantiateSiglaFormacao(ResultSet rs) throws SQLException {
        SiglaFormacao siglaFormacao = new SiglaFormacao();
        siglaFormacao.setId(rs.getInt("id"));
        siglaFormacao.setSigla(rs.getString("sigla"));

        return siglaFormacao;
    }
    
}
