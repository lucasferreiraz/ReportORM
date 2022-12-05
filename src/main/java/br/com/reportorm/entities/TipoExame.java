package br.com.reportorm.entities;

import java.io.Serializable;

public class TipoExame implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private String observacao;
    
    public TipoExame() {
    }

    public TipoExame(Integer id, String descricao, String observacao) {
        this.id = id;
        this.descricao = descricao;
        this.observacao = observacao;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "TipoExame [id=" + id + ", descricao=" + descricao + ", observacao=" + observacao + "]";
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
        TipoExame other = (TipoExame) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}
