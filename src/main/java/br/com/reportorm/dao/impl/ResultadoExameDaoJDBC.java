package br.com.reportorm.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.reportorm.dao.ResultadoExameDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.ResultadoExame;

public class ResultadoExameDaoJDBC implements ResultadoExameDao {
    
    private Connection conn;
	
	public ResultadoExameDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(ResultadoExame obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO resultado_exame "
					+ "(dt_exame,valor,composicao_id,laudo_id) "
					+ "VALUES "
					+ "(?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setDate(1, new Date(obj.getDt_exame().getTime()));
			st.setString(2, obj.getValor());
			st.setInt(3, obj.getComposicaoId());
			st.setInt(4, obj.getLaudoId());
			
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
	public void update(ResultadoExame obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE resultado_exame "
					+ "SET dt_exame = ?,valor = ?,composicao_id = ?,laudo_id = ? "
					+ "WHERE id = ?;");
			
			st.setDate(1, new Date(obj.getDt_exame().getTime()));
			st.setString(2, obj.getValor());
			st.setInt(3, obj.getComposicaoId());
			st.setInt(4, obj.getLaudoId());

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
			st = conn.prepareStatement("DELETE FROM resultado_exame WHERE Id = ?");
			
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
	public ResultadoExame findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT id, * FROM resultado_exame WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				ResultadoExame resultadoExame = instantiateResultadoExame(rs);
				return resultadoExame;
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
    public List<ResultadoExame> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<ResultadoExame> resultadoExameList = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                "SELECT * FROM resultado_exame;"
            );

            rs = st.executeQuery();

            while(rs.next()){
                ResultadoExame resultadoExame = instantiateResultadoExame(rs);
                resultadoExameList.add(resultadoExame);
            }

            return resultadoExameList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

	private ResultadoExame instantiateResultadoExame(ResultSet rs) throws SQLException {
        ResultadoExame resultadoExame = new ResultadoExame();
        resultadoExame.setId(rs.getInt("id"));
        resultadoExame.setDt_exame(rs.getDate("dt_exame"));
		resultadoExame.setValor(rs.getString("valor"));
		resultadoExame.setComposicaoId(rs.getInt("composicao_id"));
		resultadoExame.setLaudoId(rs.getInt("laudo_id"));

        return resultadoExame;
    }

}
