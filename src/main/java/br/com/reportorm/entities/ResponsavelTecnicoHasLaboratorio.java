package br.com.reportorm.entities;

import java.io.Serializable;

public class ResponsavelTecnicoHasLaboratorio implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;

    private ResponsavelTecnico responsavelTecnico;
    private Laboratorio laboratorio;
    private Integer responsavelTecnicoId;
    private Integer laboratorioId;
    
    public ResponsavelTecnicoHasLaboratorio() {
    }

    public ResponsavelTecnicoHasLaboratorio(Integer id, Integer responsavelTecnicoId, Integer laboratorioId) {
        this.id = id;
        this.responsavelTecnicoId = responsavelTecnicoId;
        this.laboratorioId = laboratorioId;
    }

    public ResponsavelTecnicoHasLaboratorio(Integer id, ResponsavelTecnico responsavelTecnico,
            Laboratorio laboratorio) {
        this.id = id;
        this.responsavelTecnico = responsavelTecnico;
        this.laboratorio = laboratorio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResponsavelTecnicoId() {
        return responsavelTecnicoId;
    }

    public void setResponsavelTecnicoId(Integer responsavelTecnicoId) {
        this.responsavelTecnicoId = responsavelTecnicoId;
    }

    public Integer getLaboratorioId() {
        return laboratorioId;
    }

    public void setLaboratorioId(Integer laboratorioId) {
        this.laboratorioId = laboratorioId;
    }

    public ResponsavelTecnico getResponsavelTecnico() {
        return responsavelTecnico;
    }

    public void setResponsavelTecnico(ResponsavelTecnico responsavelTecnico) {
        this.responsavelTecnico = responsavelTecnico;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    @Override
    public String toString() {
        return "ResponsavelTecnicoHasLaboratorio [id=" + id + ", responsavelTecnicoId=" + responsavelTecnico
                + ", laboratorioId=" + laboratorio + "]";
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
        ResponsavelTecnicoHasLaboratorio other = (ResponsavelTecnicoHasLaboratorio) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
