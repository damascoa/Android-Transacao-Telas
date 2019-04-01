package com.metre.model;

public class FormaQuitacao {
    private Integer idFormaQuitacao;
    private String descricao;
    private String forma;

    public FormaQuitacao() {
    }

    public FormaQuitacao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdFormaQuitacao() {
        return idFormaQuitacao;
    }

    public void setIdFormaQuitacao(Integer idFormaQuitacao) {
        this.idFormaQuitacao = idFormaQuitacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
