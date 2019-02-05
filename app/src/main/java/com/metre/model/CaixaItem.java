package com.metre.model;

import com.metre.model.enums.CaixaItemTipo;
import com.metre.model.enums.CaixaItemTipoMovimento;

import java.math.BigDecimal;
import java.util.Date;

public class CaixaItem {
    private Integer idCaixaItem;
    private String historico;
    private BigDecimal valor;
    private CaixaItemTipoMovimento tipoMovimento;
    private Date data;
    private Date hora;
    private String formaQuitacao;
    private Integer idPedido;
    private Integer idPedidoRecebimento;
    private Boolean recebimentoEncerramentoConta;
    private CaixaItemTipo tipo;
    private Boolean estornado;
    private Integer idCaixa;
    private Integer idPlanoContas;
    private Integer idFormaQuitacao;
}
