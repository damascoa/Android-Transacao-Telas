package com.metre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;

public class PedidoItem implements Serializable {
    private Integer idPedidoItem;
    private Integer idProduto;
    private BigDecimal preco;
    private BigDecimal quantidade;
    private BigDecimal total;
    private String nomeProduto;
    @JsonIgnore
    private Produto produto;

    public PedidoItem() {
    }

    public PedidoItem(Produto idProduto, BigDecimal quantidade, Usuario idUsuario) {
        this.idProduto = idProduto.getIdProduto();
        this.quantidade = quantidade;
        this.preco = idProduto.getPreco();
        this.total = quantidade.multiply(this.getPreco());
        this.produto = idProduto;
    }

    public void adicionarQuantidade(BigDecimal qnt) {
        setQuantidade(getQuantidade().add(qnt));
        setTotal(getQuantidade().multiply(getPreco()));
    }

    public void diminuirQuantidade(BigDecimal qnt) {
        setQuantidade(getQuantidade().subtract(qnt));
        setTotal(getQuantidade().multiply(getPreco()));
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


    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getIdPedidoItem() {
        return idPedidoItem;
    }

    public void setIdPedidoItem(Integer idPedidoItem) {
        this.idPedidoItem = idPedidoItem;
    }

    @Override
    public String toString() {
        return "PedidoItem{" +
                "idPedidoItem=" + idPedidoItem +
                ", idProduto=" + idProduto +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", total=" + total +
                ", produto=" + produto +
                '}';
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
