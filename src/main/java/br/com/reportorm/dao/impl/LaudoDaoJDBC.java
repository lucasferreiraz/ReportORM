package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.LaudoDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.Laudo;

public class LaudoDaoJDBC implements LaudoDao {

    private Connection conn;

    public LaudoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Laudo obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO laudo (assinatura_digital,dt_resultado,codigo,solicitacao_exame_id) VALUES (?,?,?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getAssinaturaDigital());
            st.setDate(2, new Date(obj.getDtExame().getTime()));
            st.setString(3, obj.getCodigo());
            st.setInt(4, obj.getSolicitacaoExameId());

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
    public void update(Laudo obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE laudo SET assinatura_digital = ?,dt_resultado = ?,codigo = ?,solicitacao_exame_id = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getAssinaturaDigital());
            st.setDate(2, new Date(obj.getDtExame().getTime()));
            st.setString(3, obj.getCodigo());
            st.setInt(4, obj.getSolicitacaoExameId());

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
                "DELETE FROM laudo WHERE id = ?;"    
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
    public Laudo findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM laudo WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Laudo laudo = instantiateLaudo(rs);
                return laudo;
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
    public List<Laudo> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Laudo> laudoList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM laudo;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                Laudo laudo = instantiateLaudo(rs);
                laudoList.add(laudo);
            }

            return laudoList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Laudo instantiateLaudo(ResultSet rs) throws SQLException {
        Laudo laudo = new Laudo();
        laudo.setId(rs.getInt("id"));
        laudo.setAssinaturaDigital(rs.getString("assinatura_digital"));
        laudo.setDtExame(rs.getDate("dt_resultado"));
        laudo.setCodigo(rs.getString("codigo"));
        laudo.setSolicitacaoExameId(rs.getInt("solicitacao_exame_id"));

        return laudo;
    }
    
}
