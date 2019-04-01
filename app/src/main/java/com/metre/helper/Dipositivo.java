package com.metre.helper;

public class Dipositivo {
    private String nome;
    private String mac;
    private String situacao;

    public Dipositivo(String nome, String mac, String situacao) {
        this.nome = nome;
        this.mac = mac;
        this.situacao = situacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Dipositivo{" +
                "nome='" + nome + '\'' +
                ", mac='" + mac + '\'' +
                ", situacao='" + situacao + '\'' +
                '}';
    }
}
