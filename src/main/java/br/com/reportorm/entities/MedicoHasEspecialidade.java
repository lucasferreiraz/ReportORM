package br.com.reportorm.entities;

import java.io.Serializable;

public class MedicoHasEspecialidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer medicoId;
    private Integer especialidadeId;
    
    public MedicoHasEspecialidade() {
    }

    public MedicoHasEspecialidade(Integer id, Integer medicoId, Integer especialidadeId) {
        this.id = id;
        this.medicoId = medicoId;
        this.especialidadeId = especialidadeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public Integer getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Integer especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    @Override
    public String toString() {
        return "MedicoHasEspecialidade [id=" + id + ", medicoId=" + medicoId + ", especialidadeId=" + especialidadeId
                + "]";
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
        MedicoHasEspecialidade other = (MedicoHasEspecialidade) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
