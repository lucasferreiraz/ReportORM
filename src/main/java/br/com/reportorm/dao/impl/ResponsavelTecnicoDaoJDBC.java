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

import br.com.reportorm.dao.ResponsavelTecnicoDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.ResponsavelTecnico;
import br.com.reportorm.entities.SiglaFormacao;

public class ResponsavelTecnicoDaoJDBC implements ResponsavelTecnicoDao {

    private Connection conn;

    public ResponsavelTecnicoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ResponsavelTecnico obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO responsavel_tecnico (nome,conselho,formacao,sigla_formacao,sigla_formacao_id) VALUES (?,?,?,?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getConselho());
            st.setString(3, obj.getFormacao());
            st.setString(4, "null");
            st.setInt(5, obj.getSiglaFormacao().getId());

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
    public void update(ResponsavelTecnico obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE responsavel_tecnico SET nome = ?,conselho = ?,formacao = ?,sigla_formacao_id = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getConselho());
            st.setString(3, obj.getFormacao());
            st.setInt(4, obj.getSiglaFormacao().getId());

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
                "DELETE FROM responsavel_tecnico WHERE id = ?;"    
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
    public ResponsavelTecnico findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
					"SELECT responsavel_tecnico.*, sigla_formacao.* "
					+ "FROM responsavel_tecnico INNER JOIN sigla_formacao "
					+ "ON responsavel_tecnico.unidade_medida_id = sigla_formacao.id "
					+ "WHERE responsavel_tecnico.id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                SiglaFormacao siglaFormacao = instantiateSiglaFormacao(rs);
                ResponsavelTecnico responsavelTecnico = instantiateResponsavelTecnico(rs, siglaFormacao);
                return responsavelTecnico;
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
	public List<ResponsavelTecnico> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT responsavel_tecnico.*, sigla_formacao.* "
					+ "FROM responsavel_tecnico INNER JOIN sigla_formacao "
					+ "ON responsavel_tecnico.unidade_medida_id = sigla_formacao.id");
			
			rs = st.executeQuery();
			
			List<ResponsavelTecnico> responsavelTecnicoList = new ArrayList<>();
			Map<Integer, SiglaFormacao> map = new HashMap<>();
			
			while (rs.next()) {
				
				SiglaFormacao uni = map.get(rs.getInt("sigla_formacao_id"));
				
				if (uni == null) {
					uni = instantiateSiglaFormacao(rs);
					map.put(rs.getInt("sigla_formacao_id"), uni);
				}
				
				ResponsavelTecnico obj = instantiateResponsavelTecnico(rs, uni);
				responsavelTecnicoList.add(obj);
			}
			return responsavelTecnicoList;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

    private ResponsavelTecnico instantiateResponsavelTecnico(ResultSet rs, SiglaFormacao siglaFormacao) throws SQLException {
        ResponsavelTecnico responsavelTecnico = new ResponsavelTecnico();
        responsavelTecnico.setId(rs.getInt("id"));
        responsavelTecnico.setNome(rs.getString("nome"));
        responsavelTecnico.setConselho(rs.getString("conselho"));
        responsavelTecnico.setFormacao(rs.getString("formacao"));
        responsavelTecnico.setSiglaFormacao(siglaFormacao);

        return responsavelTecnico;
    }

    private SiglaFormacao instantiateSiglaFormacao(ResultSet rs) throws SQLException {
        SiglaFormacao siglaFormacao = new SiglaFormacao();
        siglaFormacao.setId(rs.getInt("sigla_formacao_id"));
        siglaFormacao.setSigla(rs.getString("sigla"));

        return siglaFormacao;
    }
    
}
