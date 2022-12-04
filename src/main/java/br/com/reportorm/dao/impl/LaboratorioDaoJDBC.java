package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.LaboratorioDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.Laboratorio;

public class LaboratorioDaoJDBC implements LaboratorioDao {

    private Connection conn;

    public LaboratorioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Laboratorio obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO laboratorio (descricao,CNES,CNPJ,CRBM,nome_fantasia) VALUES (?,?,?,?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getDescricao());
            st.setString(2, obj.getCNES());
            st.setString(3, obj.getCNPJ());
            st.setString(4, obj.getCRBM());
            st.setString(5, obj.getNome_fantasia());

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
    public void update(Laboratorio obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE laboratorio SET descricao = ?,CNES = ?,CNPJ = ?,CRBM = ?,nome_fantasia = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getDescricao());
            st.setString(2, obj.getCNES());
            st.setString(3, obj.getCNPJ());
            st.setString(4, obj.getCRBM());
            st.setString(5, obj.getNome_fantasia());
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
                "DELETE FROM laboratorio WHERE id = ?;"    
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
    public Laboratorio findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM laboratorio WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Laboratorio laboratorio = instantiateLaboratorio(rs);
                return laboratorio;
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
    public List<Laboratorio> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Laboratorio> laboratorios = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM laboratorio;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                Laboratorio laboratorio = instantiateLaboratorio(rs);
                laboratorios.add(laboratorio);
            }

            return laboratorios;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Laboratorio instantiateLaboratorio(ResultSet rs) throws SQLException {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(rs.getInt("id"));
        laboratorio.setDescricao(rs.getString("descricao"));
        laboratorio.setCNES(rs.getString("cnes"));
        laboratorio.setCNPJ(rs.getString("cnpj"));
        laboratorio.setCRBM(rs.getString("crbm"));
        laboratorio.setNome_fantasia(rs.getString("nome_fantasia"));

        return laboratorio;
    }
    
}
