package com.metre;

import android.content.SharedPreferences;

import com.metre.model.Caixa;
import com.metre.model.PedidoItem;
import com.metre.model.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessaoAplicacao {
    public static Map<Object, Object> session = new HashMap<>();
    public static SharedPreferences preferencias;
    public static Usuario usuario;
    public static Caixa caixa;

    public static List<PedidoItem> itensPedido;


    public static void addItem(PedidoItem pi){
        if(itensPedido == null){
            itensPedido = new ArrayList<>();
        }
        itensPedido.add(pi);
    }

    public static void removerItem(PedidoItem pi){
        itensPedido.remove(pi);
    }
}
