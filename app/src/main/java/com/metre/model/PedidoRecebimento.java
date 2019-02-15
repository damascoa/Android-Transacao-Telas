package com.metre.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PedidoRecebimento implements Serializable {

    private Integer idFormaRecebimento;
    private BigDecimal valor;
    private Integer idAdministradora;
    private Integer idAdministradoraTipo;
    private Integer idBandeira;
    private String contasReceberJson;
    private String despesas;


    public Integer getIdFormaRecebimento() {
        return idFormaRecebimento;
    }

    public void setIdFormaRecebimento(Integer idFormaRecebimento) {
        this.idFormaRecebimento = idFormaRecebimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getIdAdministradora() {
        return idAdministradora;
    }

    public void setIdAdministradora(Integer idAdministradora) {
        this.idAdministradora = idAdministradora;
    }

    public Integer getIdAdministradoraTipo() {
        return idAdministradoraTipo;
    }

    public void setIdAdministradoraTipo(Integer idAdministradoraTipo) {
        this.idAdministradoraTipo = idAdministradoraTipo;
    }

    public Integer getIdBandeira() {
        return idBandeira;
    }

    public void setIdBandeira(Integer idBandeira) {
        this.idBandeira = idBandeira;
    }

    public String getContasReceberJson() {
        return contasReceberJson;
    }

    public void setContasReceberJson(String contasReceberJson) {
        this.contasReceberJson = contasReceberJson;
    }

    public String getDespesas() {
        return despesas;
    }

    public void setDespesas(String despesas) {
        this.despesas = despesas;
    }
}
