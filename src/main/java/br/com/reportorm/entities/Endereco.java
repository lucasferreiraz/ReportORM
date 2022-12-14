package br.com.reportorm.entities;

import java.io.Serializable;

public class Endereco implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String CEP;
    private String cidade;

    private Laboratorio laboratorio;
    private Integer laboratorioId;

    public Endereco() {
    }

    public Endereco(Integer id, String rua, String numero, String complemento, String bairro, String cEP, String cidade,
            Integer laboratorioId) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.CEP = cEP;
        this.cidade = cidade;
        this.laboratorioId = laboratorioId;
    }

    public Endereco(Integer id, String rua, String numero, String complemento, String bairro, String cEP, String cidade,
            Laboratorio laboratorio) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        CEP = cEP;
        this.cidade = cidade;
        this.laboratorio = laboratorio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String cEP) {
        this.CEP = cEP;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getLaboratorioId() {
        return laboratorioId;
    }

    public void setLaboratorioId(Integer laboratorioId) {
        this.laboratorioId = laboratorioId;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    @Override
    public String toString() {
        return "Endereco [id=" + id + ", rua=" + rua + ", numero=" + numero + ", complemento=" + complemento
                + ", bairro=" + bairro + ", CEP=" + CEP + ", cidade=" + cidade + ", laboratorioId=" + laboratorio
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
        Endereco other = (Endereco) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}
