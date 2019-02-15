package com.metre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Pedido implements Serializable {
    private Integer idPedido;
    private Integer idUsuario;
    private BigDecimal total;
    private List<PedidoItem> itens;
    private List<PedidoRecebimento> recebimentos;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }

    public List<PedidoRecebimento> getRecebimentos() {
        return recebimentos;
    }

    public void setRecebimentos(List<PedidoRecebimento> recebimentos) {
        this.recebimentos = recebimentos;
    }
}
