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

import br.com.reportorm.dao.ContatoDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.Contato;
import br.com.reportorm.entities.Laboratorio;

public class ContatoDaoJDBC implements ContatoDao {
    
    private Connection conn;
	
	public ContatoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Contato obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO contato "
					+ "(telefone, laboratorio_id) "
					+ "VALUES "
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getTelefone());

			st.setInt(2, obj.getLaboratorio().getId());
			
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
	public void update(Contato obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE contato "
					+ "SET telefone = ?, laboratorio_id = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getTelefone());
			st.setInt(2, obj.getLaboratorio().getId());

			st.setInt(6, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM contato WHERE Id = ?");
			
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
	public Contato findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT contato.*, laboratorio.* "
					+ "FROM contato INNER JOIN laboratorio "
					+ "ON contato.laboratorio_id = laboratorio.id "
					+ "WHERE contato.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Laboratorio laboratorio = instantiateLaboratorio(rs);
				Contato contato = instantiateContato(rs, laboratorio);
				return contato;
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

	private Contato instantiateContato(ResultSet rs, Laboratorio lab) throws SQLException {
		Contato contato = new Contato();
		contato.setId(rs.getInt("id"));
		contato.setTelefone(rs.getString("telefone"));
		contato.setLaboratorio(lab);
		return contato;
	}

	private Laboratorio instantiateLaboratorio(ResultSet rs) throws SQLException {
		Laboratorio laboratorio = new Laboratorio();
		laboratorio.setId(rs.getInt("laboratorio_id"));
		laboratorio.setDescricao(rs.getString("descricao"));
        laboratorio.setCNES(rs.getString("cnes"));
        laboratorio.setCNPJ(rs.getString("cnpj"));
        laboratorio.setCRBM(rs.getString("crbm"));
        laboratorio.setNome_fantasia(rs.getString("nome_fantasia"));

		return laboratorio;
	}

	@Override
	public List<Contato> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT contato.*,laboratorio.* "
					+ "FROM contato INNER JOIN laboratorio "
					+ "ON contato.laboratorio_id = laboratorio.id "
					+ "ORDER BY descricao");
			
			rs = st.executeQuery();
			
			List<Contato> contatoList = new ArrayList<>();
			Map<Integer, Laboratorio> map = new HashMap<>();
			
			while (rs.next()) {
				
				Laboratorio lab = map.get(rs.getInt("laboratorio_id"));
				
				if (lab == null) {
					lab = instantiateLaboratorio(rs);
					map.put(rs.getInt("laboratorio_id"), lab);
				}
				
				Contato obj = instantiateContato(rs, lab);
				contatoList.add(obj);
			}
			return contatoList;
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
	public List<Contato> findByLaboratorio(Laboratorio laboratorio) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT contato.*,laboratorio.* "
					+ "FROM contato INNER JOIN laboratorio "
					+ "ON contato.laboratorio_id = laboratorio.id "
					+ "WHERE contato.laboratorio_id = ? "
					+ "ORDER BY descricao");
			
			st.setInt(1, laboratorio.getId());
			
			rs = st.executeQuery();
			
			List<Contato> contatoList = new ArrayList<>();
			Map<Integer, Laboratorio> map = new HashMap<>();
			
			while (rs.next()) {
				
				Laboratorio lab = map.get(rs.getInt("laboratorio_id"));
				
				if (lab == null) {
					lab = instantiateLaboratorio(rs);
					map.put(rs.getInt("laboratorio_id"), lab);
				}
				
				Contato obj = instantiateContato(rs, lab);
				contatoList.add(obj);
			}
			return contatoList;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
