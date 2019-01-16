package com.metre.services;

import com.metre.model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    public List<Produto> getProdutos(){
        List<Produto> produtos = new ArrayList<>();
        Produto coca = new Produto(1,"Coca Cola 350 ML - Lata", new BigDecimal("2.50"));
        Produto fanta = new Produto(1,"Fanta Laranja 2 Litros", new BigDecimal("2.50"));
        Produto sukita = new Produto(1,"Sukita 2 Litros", new BigDecimal("2.50"));
        Produto sprite = new Produto(1,"Sprite 2 Litros", new BigDecimal("2.50"));
        produtos.add(coca);
        produtos.add(fanta);
        produtos.add(sprite);
        produtos.add(sukita);
        return produtos;
    }
}
