package br.com.reportorm.entities;

import java.io.Serializable;
import java.util.Date;

public class SolicitacaoExame implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nmPrescrito;
    private Date dtSolicitacao;

    private Integer consultaMedicaId;
    private Integer habilitacaoExameId;
    private Integer exameId;
    
    public SolicitacaoExame() {
    }

    public SolicitacaoExame(Integer id, String nmPrescrito, Date dtSolicitacao, Integer consultaMedicaId,
            Integer habilitacaoExameId, Integer exameId) {
        this.id = id;
        this.nmPrescrito = nmPrescrito;
        this.dtSolicitacao = dtSolicitacao;
        this.consultaMedicaId = consultaMedicaId;
        this.habilitacaoExameId = habilitacaoExameId;
        this.exameId = exameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmPrescrito() {
        return nmPrescrito;
    }

    public void setNmPrescrito(String nmPrescrito) {
        this.nmPrescrito = nmPrescrito;
    }

    public Date getDtSolicitacao() {
        return dtSolicitacao;
    }

    public void setDtSolicitacao(Date dtSolicitacao) {
        this.dtSolicitacao = dtSolicitacao;
    }

    public Integer getConsultaMedicaId() {
        return consultaMedicaId;
    }

    public void setConsultaMedicaId(Integer consultaMedicaId) {
        this.consultaMedicaId = consultaMedicaId;
    }

    public Integer getHabilitacaoExameId() {
        return habilitacaoExameId;
    }

    public void setHabilitacaoExameId(Integer habilitacaoExameId) {
        this.habilitacaoExameId = habilitacaoExameId;
    }

    public Integer getExameId() {
        return exameId;
    }

    public void setExameId(Integer exameId) {
        this.exameId = exameId;
    }

    @Override
    public String toString() {
        return "SolicitacaoExame [id=" + id + ", nmPrescrito=" + nmPrescrito + ", dtSolicitacao=" + dtSolicitacao
                + ", consultaMedicaId=" + consultaMedicaId + ", habilitacaoExameId=" + habilitacaoExameId + ", exameId="
                + exameId + "]";
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
        SolicitacaoExame other = (SolicitacaoExame) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
