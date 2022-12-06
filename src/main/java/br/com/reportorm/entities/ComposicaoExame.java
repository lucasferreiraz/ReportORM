package br.com.reportorm.entities;

import java.io.Serializable;

public class ComposicaoExame implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;

    private UnidadeMedida unidadeMedida;

    public ComposicaoExame() {
    }

    public ComposicaoExame(Integer id, String descricao, UnidadeMedida unidadeMedida) {
        this.id = id;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
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

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    @Override
    public String toString() {
        return "ComposicaoExame [id=" + id + ", descricao=" + descricao + ", unidadeMedida=" + unidadeMedida + "]";
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
        ComposicaoExame other = (ComposicaoExame) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}
