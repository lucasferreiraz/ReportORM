package br.com.reportorm.entities;

import java.io.Serializable;
import java.util.Date;

public class ConsultaMedica implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date dtConsulta;
    private String nmAtendimento;

    private Integer medicoId;
    private Integer pacienteId;
    
    public ConsultaMedica() {
    }

    public ConsultaMedica(Integer id, Date dtConsulta, String nmAtendimento, Integer medicoId, Integer pacienteId) {
        this.id = id;
        this.dtConsulta = dtConsulta;
        this.nmAtendimento = nmAtendimento;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtConsulta() {
        return dtConsulta;
    }

    public void setDtConsulta(Date dtConsulta) {
        this.dtConsulta = dtConsulta;
    }

    public String getNmAtendimento() {
        return nmAtendimento;
    }

    public void setNmAtendimento(String nmAtendimento) {
        this.nmAtendimento = nmAtendimento;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    @Override
    public String toString() {
        return "ConsultaMedica [id=" + id + ", dtConsulta=" + dtConsulta + ", nmAtendimento=" + nmAtendimento
                + ", medicoId=" + medicoId + ", pacienteId=" + pacienteId + "]";
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
        ConsultaMedica other = (ConsultaMedica) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}
