package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.reportorm.dao.ComposicaoExameDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.ComposicaoExame;
import br.com.reportorm.entities.UnidadeMedida;

public class ComposicaoExameDaoJDBC implements ComposicaoExameDao {
    
    private Connection conn;
	
	public ComposicaoExameDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(ComposicaoExame obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO composicao_exame "
					+ "(descricao, unidade_medida_id) "
					+ "VALUES "
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getDescricao());

			st.setInt(2, obj.getUnidadeMedida().getId());
			
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
	public void update(ComposicaoExame obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE composicao_exame "
					+ "SET descricao = ?, unidade_medida_id = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getDescricao());
			st.setInt(2, obj.getUnidadeMedida().getId());

			st.setInt(3, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM composicao_exame WHERE Id = ?");
			
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
	public ComposicaoExame findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT composicao_exame.*, unidade_medida.* "
					+ "FROM composicao_exame INNER JOIN unidade_medida "
					+ "ON composicao_exame.unidade_medida_id = composicao_exame.id "
					+ "WHERE composicao_exame.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				UnidadeMedida unidadeMedida = instantiateUnidadeMedida(rs);
				ComposicaoExame composicaoExame = instantiateComposicaoExame(rs, unidadeMedida);
				return composicaoExame;
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
	public List<ComposicaoExame> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT composicao_exame.*,unidade_medida.* "
					+ "FROM composicao_exame INNER JOIN unidade_medida "
					+ "ON composicao_exame.unidade_medida_id = composicao_exame.id "
					+ "ORDER BY composicao_exame.descricao");
			
			rs = st.executeQuery();
			
			List<ComposicaoExame> composicaoExameList = new ArrayList<>();
			Map<Integer, UnidadeMedida> map = new HashMap<>();
			
			while (rs.next()) {
				
				UnidadeMedida uni = map.get(rs.getInt("unidade_medida_id"));
				
				if (uni == null) {
					uni = instantiateUnidadeMedida(rs);
					map.put(rs.getInt("unidade_medida_id"), uni);
				}
				
				ComposicaoExame obj = instantiateComposicaoExame(rs, uni);
				composicaoExameList.add(obj);
			}
			return composicaoExameList;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private ComposicaoExame instantiateComposicaoExame(ResultSet rs, UnidadeMedida uni) throws SQLException {
		ComposicaoExame composicaoExame = new ComposicaoExame();
		composicaoExame.setId(rs.getInt("id"));
		composicaoExame.setDescricao(rs.getString("descricao"));
		composicaoExame.setUnidadeMedida(uni);
		return composicaoExame;
	}

	private UnidadeMedida instantiateUnidadeMedida(ResultSet rs) throws SQLException {
		UnidadeMedida unidadeMedida = new UnidadeMedida();
		unidadeMedida.setId(rs.getInt("unidade_medida_id"));
		unidadeMedida.setDescricao(rs.getString("descricao"));
		return unidadeMedida;
	}

}
