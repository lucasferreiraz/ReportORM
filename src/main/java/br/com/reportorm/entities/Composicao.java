package br.com.reportorm.entities;

import java.io.Serializable;

public class Composicao implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer exameId;
    private Integer composicaoExameId;
    private Integer valorReferenciaComposicaoExameId;
    
    public Composicao() {
    }

    public Composicao(Integer id, Integer exameId, Integer composicaoExameId,
            Integer valorReferenciaComposicaoExameId) {
        this.id = id;
        this.exameId = exameId;
        this.composicaoExameId = composicaoExameId;
        this.valorReferenciaComposicaoExameId = valorReferenciaComposicaoExameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExameId() {
        return exameId;
    }

    public void setExameId(Integer exameId) {
        this.exameId = exameId;
    }

    public Integer getComposicaoExameId() {
        return composicaoExameId;
    }

    public void setComposicaoExameId(Integer composicaoExameId) {
        this.composicaoExameId = composicaoExameId;
    }

    public Integer getValorReferenciaComposicaoExameId() {
        return valorReferenciaComposicaoExameId;
    }

    public void setValorReferenciaComposicaoExameId(Integer valorReferenciaComposicaoExameId) {
        this.valorReferenciaComposicaoExameId = valorReferenciaComposicaoExameId;
    }

    @Override
    public String toString() {
        return "Composicao [id=" + id + ", exameId=" + exameId + ", composicaoExameId=" + composicaoExameId
                + ", valorReferenciaComposicaoExameId=" + valorReferenciaComposicaoExameId + "]";
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
        Composicao other = (Composicao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}
