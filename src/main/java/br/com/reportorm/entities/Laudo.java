package br.com.reportorm.entities;

import java.io.Serializable;
import java.util.Date;

public class Laudo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String assinaturaDigital;
    private Date dtExame;
    private String codigo;

    private SolicitacaoExame solicitacaoExame;

    public Laudo() {
    }

    public Laudo(Integer id, String assinaturaDigital, Date dtExame, String codigo, SolicitacaoExame solicitacaoExame) {
        this.id = id;
        this.assinaturaDigital = assinaturaDigital;
        this.dtExame = dtExame;
        this.codigo = codigo;
        this.solicitacaoExame = solicitacaoExame;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssinaturaDigital() {
        return assinaturaDigital;
    }

    public void setAssinaturaDigital(String assinaturaDigital) {
        this.assinaturaDigital = assinaturaDigital;
    }

    public Date getDtExame() {
        return dtExame;
    }

    public void setDtExame(Date dtExame) {
        this.dtExame = dtExame;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public SolicitacaoExame getSolicitacaoExame() {
        return solicitacaoExame;
    }

    public void setSolicitacaoExame(SolicitacaoExame solicitacaoExame) {
        this.solicitacaoExame = solicitacaoExame;
    }

    @Override
    public String toString() {
        return "Laudo [id=" + id + ", assinaturaDigital=" + assinaturaDigital + ", dtExame=" + dtExame + ", codigo="
                + codigo + ", solicitacaoExame=" + solicitacaoExame + "]";
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
        Laudo other = (Laudo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
