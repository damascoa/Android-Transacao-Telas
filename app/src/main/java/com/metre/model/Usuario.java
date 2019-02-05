package com.metre.model;

import com.metre.SessaoAplicacao;

import java.io.Serializable;

public class Usuario implements Serializable {
    private Integer idUsuario;
    private String usuario;
    private String nome;
    private String foto;
    private Integer caixaCorrente;
    private String senha;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        if(foto.startsWith("http://replaceip")){
            foto= foto.replace("replaceip",SessaoAplicacao.preferencias.getString("ip","192.168.1.199"));
        }
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getCaixaCorrente() {
        return caixaCorrente;
    }

    public void setCaixaCorrente(Integer caixaCorrente) {
        this.caixaCorrente = caixaCorrente;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", usuario='" + usuario + '\'' +
                ", nome='" + nome + '\'' +
                ", foto='" + foto + '\'' +
                ", caixaCorrente=" + caixaCorrente +
                ", senha='" + senha + '\'' +
                '}';
    }
}
