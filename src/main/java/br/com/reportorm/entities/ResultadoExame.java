package br.com.reportorm.entities;

import java.io.Serializable;
import java.util.Date;

public class ResultadoExame implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date dt_exame;
    private String valor;

    private Composicao composicao;
    private Laudo laudo;
    private Integer composicaoId;
    private Integer laudoId;
    
    public ResultadoExame() {
    }

    public ResultadoExame(Integer id, Date dt_exame, String valor, Integer composicaoId, Integer laudoId) {
        this.id = id;
        this.dt_exame = dt_exame;
        this.valor = valor;
        this.composicaoId = composicaoId;
        this.laudoId = laudoId;
    }

    public ResultadoExame(Integer id, Date dt_exame, String valor, Composicao composicao, Laudo laudo) {
        this.id = id;
        this.dt_exame = dt_exame;
        this.valor = valor;
        this.composicao = composicao;
        this.laudo = laudo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDt_exame() {
        return dt_exame;
    }

    public void setDt_exame(Date dt_exame) {
        this.dt_exame = dt_exame;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getComposicaoId() {
        return composicaoId;
    }

    public void setComposicaoId(Integer composicaoId) {
        this.composicaoId = composicaoId;
    }

    public Integer getLaudoId() {
        return laudoId;
    }

    public void setLaudoId(Integer laudoId) {
        this.laudoId = laudoId;
    }

    public Composicao getComposicao() {
        return composicao;
    }

    public void setComposicao(Composicao composicao) {
        this.composicao = composicao;
    }

    public Laudo getLaudo() {
        return laudo;
    }

    public void setLaudo(Laudo laudo) {
        this.laudo = laudo;
    }

    @Override
    public String toString() {
        return "ResultadoExame [id=" + id + ", dt_exame=" + dt_exame + ", valor=" + valor + ", composicaoId="
                + composicaoId + ", laudoId=" + laudoId + "]";
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
        ResultadoExame other = (ResultadoExame) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}
