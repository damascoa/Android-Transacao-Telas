package com.metre.model;

import com.metre.model.enums.Situacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable {
    private Integer idPeido;
    private Usuario idUsuario;
    private Situacao situacao;
    private Date dtAbertura;
    private Date dtFechamento;
    private BigDecimal totalPedido;
    private BigDecimal valorRecebido;
    private Integer idMesa = 999999999;

    private List<PedidoItem> itensPedido = new ArrayList<>();

    public Pedido(Usuario idUsuario, List<PedidoItem> itensPedido) {
        this.idUsuario = idUsuario;
        this.itensPedido = itensPedido;
        this.dtAbertura = new Date();
        this.dtFechamento = new Date();
        this.itensPedido = itensPedido;
        this.totalPedido =  itensPedido.stream().map(PedidoItem::getTotal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        this.valorRecebido = BigDecimal.ZERO;
        this.situacao = Situacao.ABERTO;

    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<PedidoItem> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<PedidoItem> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
