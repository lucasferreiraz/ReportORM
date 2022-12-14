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

import br.com.reportorm.dao.EnderecoDao;
import br.com.reportorm.database.DB;
import br.com.reportorm.database.DbException;
import br.com.reportorm.entities.Endereco;
import br.com.reportorm.entities.Laboratorio;

public class EnderecoDaoJDBC implements EnderecoDao {

    private Connection conn;

    public EnderecoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Endereco obj) {
       PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "INSERT INTO endereco (rua,numero,complemento,bairro,CEP,cidade,laboratorio_id) VALUES (?,?,?,?,?,?,?);",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getRua());
            st.setString(2, obj.getNumero());
            st.setString(3, obj.getComplemento());
            st.setString(4, obj.getBairro());
            st.setString(5, obj.getCEP());
            st.setString(6, obj.getCidade());
            st.setInt(7, obj.getLaboratorio().getId());

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
    public void update(Endereco obj) {
        PreparedStatement st = null;
       
       try {
            st = conn.prepareStatement(
                "UPDATE endereco SET rua = ?,numero = ?,complemento = ?,bairro = ?,CEP = ?,cidade = ?,laboratorio_id = ? WHERE id = ?;",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getRua());
            st.setString(2, obj.getNumero());
            st.setString(3, obj.getComplemento());
            st.setString(4, obj.getBairro());
            st.setString(5, obj.getCEP());
            st.setString(6, obj.getCidade());
            st.setInt(7, obj.getLaboratorio().getId());

            st.setInt(8, obj.getId());

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
                "DELETE FROM endereco WHERE id = ?;"    
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
    public Endereco findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT endereco.*, laboratorio.* "
                            + "FROM endereco INNER JOIN laboratorio "
                            + "ON endereco.laboratorio_id = laboratorio.id "
                            + "WHERE endereco.id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Laboratorio laboratorio = instantiateLaboratorio(rs);
                Endereco endereco = instantiateEndereco(rs, laboratorio);
                return endereco;
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
    public List<Endereco> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT endereco.*, laboratorio.* "
                            + "FROM endereco INNER JOIN laboratorio "
                            + "ON endereco.laboratorio_id = laboratorio.id");

            rs = st.executeQuery();

            List<Endereco> enderecoList = new ArrayList<>();
            Map<Integer, Laboratorio> map = new HashMap<>();

            while (rs.next()) {

                Laboratorio uni = map.get(rs.getInt("unidade_medida_id"));

                if (uni == null) {
                    uni = instantiateLaboratorio(rs);
                    map.put(rs.getInt("unidade_medida_id"), uni);
                }

                Endereco obj = instantiateEndereco(rs, uni);
                enderecoList.add(obj);
            }
            return enderecoList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Endereco instantiateEndereco(ResultSet rs, Laboratorio laboratorio) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("id"));
        endereco.setRua(rs.getString("rua"));
        endereco.setNumero(rs.getString("numero"));
        endereco.setComplemento(rs.getString("complemento"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setCEP(rs.getString("CEP"));
        endereco.setCidade(rs.getString("cidade"));
        endereco.setLaboratorio(laboratorio);

        return endereco;
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
    
}
