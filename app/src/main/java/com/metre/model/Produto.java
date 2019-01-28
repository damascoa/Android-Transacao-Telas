package com.metre.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable {
    private Integer idProduto;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal custo;
    private Grupo grupo;
    private String evento;
    private String foto;

    public Produto() {

    }

    public Produto(Integer idProduto, String descricao, BigDecimal preco) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.preco = preco;
    }


    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto=" + idProduto +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", custo=" + custo +
                ", grupo=" + grupo +
                ", evento='" + evento + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
