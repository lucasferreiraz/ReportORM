package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.HabilitacaoExameDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.HabilitacaoExame;

public class HabilitacaoExameDaoJDBC implements HabilitacaoExameDao {

    private Connection conn;

    public HabilitacaoExameDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(HabilitacaoExame obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO habilitacao_exame (observacao,custo,laboratorio_id,tipo_exame_id) VALUES (?,?,?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getObservacao());
            st.setLong(2, obj.getCusto());
            st.setInt(3, obj.getLaboratorioId());
            st.setInt(4, obj.getTipoExameId());

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
    public void update(HabilitacaoExame obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE habilitacao_exame SET observacao = ?,custo = ?,laboratorio_id = ?,tipo_exame_id = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getObservacao());
            st.setLong(2, obj.getCusto());
            st.setInt(3, obj.getLaboratorioId());
            st.setInt(4, obj.getTipoExameId());

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
                "DELETE FROM habilitacao_exame WHERE id = ?;"    
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
    public HabilitacaoExame findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM habilitacao_exame WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                HabilitacaoExame habilitacaoExame = instantiateHabilitacaoExame(rs);
                return habilitacaoExame;
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
    public List<HabilitacaoExame> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<HabilitacaoExame> habilitacaoExameList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM habilitacao_exame;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                HabilitacaoExame habilitacaoExame = instantiateHabilitacaoExame(rs);
                habilitacaoExameList.add(habilitacaoExame);
            }

            return habilitacaoExameList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private HabilitacaoExame instantiateHabilitacaoExame(ResultSet rs) throws SQLException {
        HabilitacaoExame habilitacaoExame = new HabilitacaoExame();
        habilitacaoExame.setId(rs.getInt("id"));
        habilitacaoExame.setObservacao(rs.getString("observacao"));
        habilitacaoExame.setCusto(rs.getLong("custo"));
        habilitacaoExame.setLaboratorioId(rs.getInt("laboratorio_id"));
        habilitacaoExame.setTipoExameId(rs.getInt("tipo_exame_id"));

        return habilitacaoExame;
    }
    
}
