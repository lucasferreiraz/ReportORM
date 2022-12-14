package br.com.reportorm.entities;

import java.io.Serializable;

public class ValorReferenciaComposicaoExame implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String valorMinimo;
    private String valorMaximo;
    private String limitadorMinimo;
    private String limitadorMaximo;

    private UnidadeMedida unidadeMedida;

    public ValorReferenciaComposicaoExame() {
    }

    public ValorReferenciaComposicaoExame(Integer id, String valorMinimo, String valorMaximo, String limitadorMinimo,
            String limitadorMaximo, UnidadeMedida unidadeMedida) {
        this.id = id;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.limitadorMinimo = limitadorMinimo;
        this.limitadorMaximo = limitadorMaximo;
        this.unidadeMedida = unidadeMedida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(String valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public String getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(String valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public String getLimitadorMinimo() {
        return limitadorMinimo;
    }

    public void setLimitadorMinimo(String limitadorMinimo) {
        this.limitadorMinimo = limitadorMinimo;
    }

    public String getLimitadorMaximo() {
        return limitadorMaximo;
    }

    public void setLimitadorMaximo(String limitadorMaximo) {
        this.limitadorMaximo = limitadorMaximo;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    @Override
    public String toString() {
        return "ValorReferenciaComposicaoExame [id=" + id + ", valorMinimo=" + valorMinimo + ", valorMaximo="
                + valorMaximo + ", limitadorMinimo=" + limitadorMinimo + ", limitadorMaximo=" + limitadorMaximo
                + ", unidadeMedidaId=" + unidadeMedida + "]";
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
        ValorReferenciaComposicaoExame other = (ValorReferenciaComposicaoExame) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
