package com.metre.model.enums;

public enum Situacao {

    FINALIZADO("Finalizado"),ABERTO("Aberto");
    private String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
