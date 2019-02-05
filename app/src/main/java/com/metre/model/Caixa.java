package com.metre.model;

import java.math.BigDecimal;
import java.util.Date;

public class Caixa {
    private Integer idCaixa;
    private Date dtCaixa;
    private String situacao;
    private BigDecimal totalEntradas;
    private BigDecimal totalSaidas;
    private BigDecimal saldo;
    private String tipo;
    private String terminal;
    private String periodo;
    private Date dtFechamento;
    private BigDecimal trocoAbertura;
    private Boolean conferido;
    private Date horaAbertura;
    private Integer idUsuario;
    private Integer idEmpresa;

    public Integer getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(Integer idCaixa) {
        this.idCaixa = idCaixa;
    }

    public Date getDtCaixa() {
        return dtCaixa;
    }

    public void setDtCaixa(Date dtCaixa) {
        this.dtCaixa = dtCaixa;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(BigDecimal totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public BigDecimal getTotalSaidas() {
        return totalSaidas;
    }

    public void setTotalSaidas(BigDecimal totalSaidas) {
        this.totalSaidas = totalSaidas;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getDtFechamento() {
        return dtFechamento;
    }

    public void setDtFechamento(Date dtFechamento) {
        this.dtFechamento = dtFechamento;
    }

    public BigDecimal getTrocoAbertura() {
        return trocoAbertura;
    }

    public void setTrocoAbertura(BigDecimal trocoAbertura) {
        this.trocoAbertura = trocoAbertura;
    }

    public Boolean getConferido() {
        return conferido;
    }

    public void setConferido(Boolean conferido) {
        this.conferido = conferido;
    }

    public Date getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(Date horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
