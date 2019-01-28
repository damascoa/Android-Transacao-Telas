package com.metre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PedidoItem implements Serializable {
    private Integer idPedidoItem;
    private Pedido idPedido;
    private Produto idProduto;
    private BigDecimal quantidade;
    private BigDecimal preco ;
    private BigDecimal custo ;
    private BigDecimal total;
    private Date dtPedido = new Date();
    private Usuario idUsuario;
    private String situacao = "Registrado";
    private String produto;
    private Boolean baixado;
    private String evento ;
    private Boolean balanca = Boolean.FALSE;
    private Boolean incideTaxaServico = Boolean.FALSE;
    private BigDecimal taxaServicoValor = BigDecimal.ZERO;
    private Boolean itemPromocional = Boolean.FALSE;

    public PedidoItem(Produto idProduto, BigDecimal quantidade, Usuario idUsuario) {
        this.idProduto = idProduto;
        this.produto = this.idProduto.getDescricao();
        this.quantidade = quantidade;
        this.idUsuario = idUsuario;
        this.preco = idProduto.getPreco();
        this.total = quantidade.multiply(this.getPreco());
    }

    public void adicionarQuantidade(BigDecimal qnt){
        setQuantidade(getQuantidade().add(qnt));
        setTotal(getQuantidade().multiply(getPreco()));
    }
    public void diminuirQuantidade(BigDecimal qnt){
        setQuantidade(getQuantidade().subtract(qnt));
        setTotal(getQuantidade().multiply(getPreco()));
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }
}
