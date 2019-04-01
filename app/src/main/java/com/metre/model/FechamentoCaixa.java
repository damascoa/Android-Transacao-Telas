package com.metre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class FechamentoCaixa implements Serializable {

    public String usuario;
    private String entradas;
    private String saidas;
    private List<FechamentoCaixaDetalhe> detalhes;

    public FechamentoCaixa() {
        super();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEntradas() {
        return entradas;
    }

    public void setEntradas(String entradas) {
        this.entradas = entradas;
    }

    public String getSaidas() {
        return saidas;
    }

    public void setSaidas(String saidas) {
        this.saidas = saidas;
    }

    public List<FechamentoCaixaDetalhe> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(List<FechamentoCaixaDetalhe> detalhes) {
        this.detalhes = detalhes;
    }

    @Override
    public String toString() {
        return "FechamentoCaixa{" +
                "usuario='" + usuario + '\'' +
                ", entradas='" + entradas + '\'' +
                ", saidas='" + saidas + '\'' +
                ", detalhes=" + detalhes +
                '}';
    }
}
