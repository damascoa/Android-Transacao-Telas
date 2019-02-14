package com.metre.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.metre.helper.Dipositivo;
import com.metre.projetotransacaotelas.R;

import java.util.List;

public class ViewHolderImpressora extends RecyclerView.ViewHolder  implements View.OnCreateContextMenuListener {
    public List<Dipositivo> dispositivos;
    public final View view;
    public final TextView txtNome;
    public final TextView txtMac;




    public ViewHolderImpressora(@NonNull View itemView, List<Dipositivo> dispositivos, final Context context) {
        super(itemView);
        this.view = itemView;
        txtNome     = itemView.findViewById(R.id.txtNome);
        txtMac =    itemView.findViewById(R.id.txtMac);

        itemView.setOnCreateContextMenuListener(this);



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dispositivos.size() > 0) {
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