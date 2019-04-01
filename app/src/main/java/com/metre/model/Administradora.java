package com.metre.model;

import java.util.List;

public class Administradora {
    private Integer idAdministradora;
    private String nome;
    private List<AdministradoraTipo> administradoraTipo;



    public Administradora() {
    }

    public Integer getIdAdministradora() {
        return idAdministradora;
    }

    public void setIdAdministradora(Integer idAdministradora) {
        this.idAdministradora = idAdministradora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<AdministradoraTipo> getAdministradoraTipo() {
        return administradoraTipo;
    }

    public void setAdministradoraTipo(List<AdministradoraTipo> administradoraTipo) {
        this.administradoraTipo = administradoraTipo;
    }

}
