package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.SolicitacaoExameDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.SolicitacaoExame;

public class SolicitacaoExameDaoJDBC implements SolicitacaoExameDao {

    private Connection conn;

    public SolicitacaoExameDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(SolicitacaoExame obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO solicitacao_exame (nm_prescrito,dt_solicitacao,consulta_medica_id,habilitacao_exame_id, exame_id) VALUES (?,?,?,?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNmPrescrito());
            st.setDate(2, new Date(obj.getDtSolicitacao().getTime()));
            st.setInt(3, obj.getConsultaMedica().getId());
            st.setInt(4, obj.getHabilitacaoExame().getId());
            st.setInt(5, obj.getExame().getId());

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
    public void update(SolicitacaoExame obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE solicitacao_exame SET nm_prescrito = ?,dt_solicitacao = ?,consulta_medica_id = ?,habilitacao_exame_id = ?,exame_id = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNmPrescrito());
            st.setDate(2, new Date(obj.getDtSolicitacao().getTime()));
            st.setInt(3, obj.getConsultaMedica().getId());
            st.setInt(4, obj.getHabilitacaoExame().getId());
            st.setInt(5, obj.getExame().getId());

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
                "DELETE FROM solicitacao_exame WHERE id = ?;"    
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
    public SolicitacaoExame findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM solicitacao_exame WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                SolicitacaoExame solicitacaoExame = instantiateSolicitacaoExame(rs);
                return solicitacaoExame;
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
    public List<SolicitacaoExame> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<SolicitacaoExame> solicitacaoExameList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM solicitacao_exame;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                SolicitacaoExame solicitacaoExame = instantiateSolicitacaoExame(rs);
                solicitacaoExameList.add(solicitacaoExame);
            }

            return solicitacaoExameList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private SolicitacaoExame instantiateSolicitacaoExame(ResultSet rs) throws SQLException {
        SolicitacaoExame solicitacaoExame = new SolicitacaoExame();
        solicitacaoExame.setId(rs.getInt("id"));
        solicitacaoExame.setNmPrescrito(rs.getString("nm_prescrito"));
        solicitacaoExame.setConsultaMedicaId(rs.getInt("consulta_medica_id"));
        solicitacaoExame.setDtSolicitacao(rs.getDate("dt_solicitacao"));
        solicitacaoExame.setHabilitacaoExameId(rs.getInt("habilitacao_exame_id"));
        solicitacaoExame.setExameId(rs.getInt("exame_id"));

        return solicitacaoExame;
    }
    
}
