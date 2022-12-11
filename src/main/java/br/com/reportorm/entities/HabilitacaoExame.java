package br.com.reportorm.entities;

import java.io.Serializable;

public class HabilitacaoExame implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String observacao;
    private Long custo;

    private Integer laboratorioId;
    private Integer tipoExameId;
    
    public HabilitacaoExame() {
    }

    public HabilitacaoExame(Integer id, String observacao, Long custo, Integer laboratorioId, Integer tipoExameId) {
        this.id = id;
        this.observacao = observacao;
        this.custo = custo;
        this.laboratorioId = laboratorioId;
        this.tipoExameId = tipoExameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getCusto() {
        return custo;
    }

    public void setCusto(Long custo) {
        this.custo = custo;
    }

    public Integer getLaboratorioId() {
        return laboratorioId;
    }

    public void setLaboratorioId(Integer laboratorioId) {
        this.laboratorioId = laboratorioId;
    }

    public Integer getTipoExameId() {
        return tipoExameId;
    }

    public void setTipoExameId(Integer tipoExameId) {
        this.tipoExameId = tipoExameId;
    }

    @Override
    public String toString() {
        return "HabilitacaoExame [id=" + id + ", observacao=" + observacao + ", custo=" + custo + ", laboratorioId="
                + laboratorioId + ", tipoExameId=" + tipoExameId + "]";
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
        HabilitacaoExame other = (HabilitacaoExame) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
