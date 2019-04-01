package com.metre.model;

import java.math.BigDecimal;

public class FechamentoCaixaDetalhe {
    private String forma;
    private String total;

    public FechamentoCaixaDetalhe() {
    }



    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "FechamentoCaixaDetalhe{" +
                "forma='" + forma + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
