package com.metre.model.enums;

public enum CaixaItemTipoMovimento {
    CREDITO("C"), DEBITO("D");
    private String descricao;

    CaixaItemTipoMovimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
