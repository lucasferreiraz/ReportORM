package br.com.reportorm.entities;

import java.io.Serializable;

public class ResponsavelTecnico implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String conselho;
    private String formacao;

    private SiglaFormacao siglaFormacao;
    private Integer siglaFormacaoId;

    public ResponsavelTecnico() {
    }

    public ResponsavelTecnico(Integer id, String nome, String conselho, String formacao, Integer siglaFormacaoId) {
        this.id = id;
        this.nome = nome;
        this.conselho = conselho;
        this.formacao = formacao;
        this.siglaFormacaoId = siglaFormacaoId;
    }

    public ResponsavelTecnico(Integer id, String nome, String conselho, String formacao, SiglaFormacao siglaFormacao) {
        this.id = id;
        this.nome = nome;
        this.conselho = conselho;
        this.formacao = formacao;
        this.siglaFormacao = siglaFormacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConselho() {
        return conselho;
    }

    public void setConselho(String conselho) {
        this.conselho = conselho;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public Integer getSiglaFormacaoId() {
        return siglaFormacaoId;
    }

    public void setSiglaFormacaoId(Integer siglaFormacaoId) {
        this.siglaFormacaoId = siglaFormacaoId;
    }

    public SiglaFormacao getSiglaFormacao() {
        return siglaFormacao;
    }

    public void setSiglaFormacao(SiglaFormacao siglaFormacao) {
        this.siglaFormacao = siglaFormacao;
    }

    @Override
    public String toString() {
        return "ResponsavelTecnico [id=" + id + ", nome=" + nome + ", conselho=" + conselho + ", formacao=" + formacao
                + ", siglaFormacaoId=" + siglaFormacao + "]";
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
        ResponsavelTecnico other = (ResponsavelTecnico) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
