package br.com.reportorm.entities;

import java.io.Serializable;

public class SiglaFormacao implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String sigla;
    
    public SiglaFormacao() {
    }

    public SiglaFormacao(Integer id, String sigla) {
        this.id = id;
        this.sigla = sigla;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "SiglaFormacao [id=" + id + ", sigla=" + sigla + "]";
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
        SiglaFormacao other = (SiglaFormacao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
