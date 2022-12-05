package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.MaterialExameDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.MaterialExame;

public class MaterialExameDaoJDBC implements MaterialExameDao {

    private Connection conn;

    public MaterialExameDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(MaterialExame obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO material_exame (material,observacao) VALUES (?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getMaterial());
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
    public void update(MaterialExame obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE material_exame SET material = ?,observacao = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getMaterial());
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
                "DELETE FROM material_exame WHERE id = ?;"    
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
    public MaterialExame findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT id, * FROM material_exame WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                MaterialExame materialExame = instantiateMaterialExame(rs);
                return materialExame;
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
    public List<MaterialExame> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<MaterialExame> materialExameList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM tipo_exame;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                MaterialExame materialExame = instantiateMaterialExame(rs);
                materialExameList.add(materialExame);
            }

            return materialExameList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private MaterialExame instantiateMaterialExame(ResultSet rs) throws SQLException {
        MaterialExame materialExame = new MaterialExame();
        materialExame.setId(rs.getInt("id"));
        materialExame.setMaterial(rs.getString("material"));
        materialExame.setObservacao(rs.getString("observacao"));

        return materialExame;
    }
    
}
