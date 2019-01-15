package com.metre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PedidoItem implements Serializable {
    private Integer idPedidoItem;
    private Pedido idPedido;
    private Produto idProduto;
    private BigDecimal quantidade;
    private BigDecimal custo = idProduto.getCusto();
    private BigDecimal total;
    private Date dtPedido = new Date();
    private Usuario idUsuario;
    private String situacao = "Registrado";
    private String produto = idProduto.getDescricao();
    private Boolean baixado;
    private String evento = idProduto.getEvento();
    private Boolean balanca = Boolean.FALSE;
    private Boolean incideTaxaServico = Boolean.FALSE;
    private BigDecimal taxaServicoValor = BigDecimal.ZERO;
    private Boolean itemPromocional = Boolean.FALSE;

    public PedidoItem(Produto idProduto, BigDecimal quantidade, Usuario idUsuario) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.idUsuario = idUsuario;
        this.total = this.quantidade.multiply(this.idProduto.getPreco());
    }

    public Integer getIdPedidoItem() {
        return idPedidoItem;
    }

    public void setIdPedidoItem(Integer idPedidoItem) {
        this.idPedidoItem = idPedidoItem;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
}
