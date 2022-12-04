package br.com.reportorm.entities;

import java.io.Serializable;

public class Laboratorio implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private String CNES;
    private String CNPJ;
    private String CRBM;
    private String nome_fantasia;
    
    public Laboratorio() {
    }

    public Laboratorio(Integer id, String descricao, String CNES, String CNPJ, String CRBM, String nome_fantasia) {
        this.id = id;
        this.descricao = descricao;
        this.CNES = CNES;
        this.CNPJ = CNPJ;
        this.CRBM = CRBM;
        this.nome_fantasia = nome_fantasia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCNES() {
        return CNES;
    }

    public void setCNES(String cNES) {
        CNES = cNES;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cNPJ) {
        CNPJ = cNPJ;
    }

    public String getCRBM() {
        return CRBM;
    }

    public void setCRBM(String cRBM) {
        CRBM = cRBM;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    @Override
    public String toString() {
        return "Laboratorio [id=" + id + ", descricao=" + descricao + ", CNES=" + CNES + ", CNPJ=" + CNPJ + ", CRBM="
                + CRBM + ", nome_fantasia=" + nome_fantasia + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Laboratorio other = (Laboratorio) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
