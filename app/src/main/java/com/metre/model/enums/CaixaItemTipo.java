package com.metre.model.enums;

public enum CaixaItemTipo {
    ABERTURA("Abertura"),
    ADIANTAMENTO("Adiantamento"),
    CANCELAMENTO("Cancelamento"),
    ESTORNO("Estorno"),
    OUTRO("Outro"),
    PAGAMENTO("Pagamento"),
    RECEBIMENTO("Recebimento"),
    SANGRIA("Sangria"),
    SUPRIMENTO("Suprimento");

    private String tipo;

    private CaixaItemTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
