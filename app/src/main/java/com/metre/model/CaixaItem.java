package com.metre.model;

import com.metre.model.enums.CaixaItemTipo;
import com.metre.model.enums.CaixaItemTipoMovimento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaixaItem {
    private Integer idCaixaItem;
    private String historico;
    private BigDecimal valor;
    private CaixaItemTipoMovimento tipoMovimento;
    private Date data;
    private Date hora;
    private String formaQuitacao;
    private Integer idPedido;
    private Integer idPedidoRecebimento;
    private Boolean recebimentoEncerramentoConta;
    private CaixaItemTipo tipo;
    private Boolean estornado;
    private Integer idCaixa;
    private Integer idPlanoContas;
    private Integer idFormaQuitacao;

    public CaixaItem() {
    }

    public CaixaItem(String historico, BigDecimal valor, String formaQuitacao) {
        this.historico = historico;
        this.valor = valor;
        this.formaQuitacao = formaQuitacao;
    }

    public Integer getIdCaixaItem() {
        return idCaixaItem;
    }

    public void setIdCaixaItem(Integer idCaixaItem) {
        this.idCaixaItem = idCaixaItem;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public CaixaItemTipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(CaixaItemTipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getFormaQuitacao() {
        return formaQuitacao;
    }

    public void setFormaQuitacao(String formaQuitacao) {
        this.formaQuitacao = formaQuitacao;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPedidoRecebimento() {
        return idPedidoRecebimento;
    }

    public void setIdPedidoRecebimento(Integer idPedidoRecebimento) {
        this.idPedidoRecebimento = idPedidoRecebimento;
    }

    public Boolean getRecebimentoEncerramentoConta() {
        return recebimentoEncerramentoConta;
    }

    public void setRecebimentoEncerramentoConta(Boolean recebimentoEncerramentoConta) {
        this.recebimentoEncerramentoConta = recebimentoEncerramentoConta;
    }

    public CaixaItemTipo getTipo() {
        return tipo;
    }

    public void setTipo(CaixaItemTipo tipo) {
        this.tipo = tipo;
    }

    public Boolean getEstornado() {
        return estornado;
    }

    public void setEstornado(Boolean estornado) {
        this.estornado = estornado;
    }

    public Integer getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(Integer idCaixa) {
        this.idCaixa = idCaixa;
    }

    public Integer getIdPlanoContas() {
        return idPlanoContas;
    }

    public void setIdPlanoContas(Integer idPlanoContas) {
        this.idPlanoContas = idPlanoContas;
    }

    public Integer getIdFormaQuitacao() {
        return idFormaQuitacao;
    }

    public void setIdFormaQuitacao(Integer idFormaQuitacao) {
        this.idFormaQuitacao = idFormaQuitacao;
    }

    public List<CaixaItem> simulate(){
        List<CaixaItem> movimentos = new ArrayList<>();
        for(int i= 0; i < 100; i++) {
            movimentos.add(new CaixaItem("Recebimento do pedido "+i+1, new BigDecimal(100.00),"Dinheiro"));
        }
        return movimentos;
    }
}
