package com.metre.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.metre.model.CaixaItem;
import com.metre.model.PedidoRecebimento;
import com.metre.projetotransacaotelas.R;

import java.util.List;

public class ViewHolderRecebimento extends RecyclerView.ViewHolder  implements View.OnCreateContextMenuListener {
    public List<PedidoRecebimento> recebimentos;
    public final View view;

    public final TextView txtFormaRecebimento;
    public final TextView txtValorRecebimento;



    public ViewHolderRecebimento(@NonNull View itemView, List<PedidoRecebimento> recebimentos, final Context context) {
        super(itemView);
        this.view = itemView;

        txtFormaRecebimento =    itemView.findViewById(R.id.txtFormaRecebimento);
        txtValorRecebimento =  itemView.findViewById(R.id.txtValorRecebimento);
        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recebimentos.size() > 0) {
                /*    Produto produtoSelecionado = produtos.get(getLayoutPosition());
                    Intent it = new Intent(context, ProdutoActivity.class);
                    it.putExtra("produto",produtoSelecionado);
                    Bundle bundle = it.getExtras();
                    ((AppCompatActivity) context).startActivityForResult(it, 0,bundle);
                */
                }
            }
        });


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Ações");
        MenuItem callItem = menu.add(0, v.getId(), 0, "Cancelar");//groupId, itemId, order, title
        callItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.out.println("HELLLLLLLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                return false;
            }
        });
    }



}