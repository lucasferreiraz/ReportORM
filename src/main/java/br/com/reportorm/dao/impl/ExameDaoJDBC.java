package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.ExameDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.Exame;

public class ExameDaoJDBC implements ExameDao {
    
    private Connection conn;
	
	public ExameDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Exame obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO exame "
					+ "(tipo_exame_id,material_exame_id,descricao,metodo) "
					+ "VALUES "
					+ "(?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			
				st.setInt(1, obj.getTipoExame().getId());
				st.setInt(2, obj.getMaterialExame().getId());
				st.setString(3, obj.getDescricao());
				st.setString(4, obj.getMetodo());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Exame obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE exame "
					+ "SET tipo_exame_id = ?,material_exame_id = ?,descricao = ?,metodo = ? "
					+ "WHERE id = ?;");
			
			st.setInt(1, obj.getTipoExame().getId());
			st.setInt(2, obj.getMaterialExame().getId());
			st.setString(3, obj.getDescricao());
			st.setString(4, obj.getMetodo());

			st.setInt(5, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM exame WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Exame findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT id, * FROM exame WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Exame exame = instantiateExame(rs);
				return exame;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
    public List<Exame> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Exame> exameList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM exame;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                Exame exame = instantiateExame(rs);
                exameList.add(exame);
            }

            return exameList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

	private Exame instantiateExame(ResultSet rs) throws SQLException {
        Exame exame = new Exame();
        exame.setId(rs.getInt("id"));
        exame.setTipoExameId(rs.getInt("tipo_exame_id"));
		exame.setDescricao(rs.getString("descricao"));
		exame.setMaterialExameId(rs.getInt("material_exame_id"));
		exame.setMetodo(rs.getString("metodo"));

        return exame;
    }

}
