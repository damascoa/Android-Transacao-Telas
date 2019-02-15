package com.metre.model;

import java.math.BigDecimal;

public class AdministradoraTipo {
    private Integer idAdministradoraTipo;
    private Integer idAdministradora;
    private String tipo;
    private BigDecimal taxa;
    private Integer prazo;

    public Integer getIdAdministradoraTipo() {
        return idAdministradoraTipo;
    }

    public void setIdAdministradoraTipo(Integer idAdministradoraTipo) {
        this.idAdministradoraTipo = idAdministradoraTipo;
    }

    public Integer getIdAdministradora() {
        return idAdministradora;
    }

    public void setIdAdministradora(Integer idAdministradora) {
        this.idAdministradora = idAdministradora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }
}
