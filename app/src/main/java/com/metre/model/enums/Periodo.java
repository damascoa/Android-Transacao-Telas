package com.metre.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Periodo {

    UMMANHA("1 - Manhã"),
    UMTARDE("1 - Tarde"),
    UMNOITE("1 - Noite"),
    DOISNOITE("2 - Manhã"),
    DOISMANHA("2 - Tarde"),
    DOISTARDE("2 - Noite"),
    TRESNOITE("3 - Manhã"),
    TRESMANHA("3 - Tarde"),
    TRESTARDE("3 - Noite");

    private String periodo;

    Periodo(String periodo) {
        this.periodo = periodo;
    }

    public static List<String> valores() {
        List<String> saida = new ArrayList<>();
        for (Periodo p : values()) {
            saida.add(p.getPeriodo());
        }
        return saida;
    }

    public String getPeriodo() {
        return periodo;
    }

    @Override
    public String toString() {
        return periodo;
    }

}
