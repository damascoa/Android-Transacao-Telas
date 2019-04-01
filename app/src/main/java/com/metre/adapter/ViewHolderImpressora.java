package com.metre.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.metre.ServicoImpressao;
import com.metre.SessaoAplicacao;
import com.metre.helper.Dipositivo;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.fragment.ImpressorasFragment;

import java.io.IOException;
import java.util.List;

public class ViewHolderImpressora extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener  {
    public List<Dipositivo> dispositivos;
    public  View view;
    public  TextView txtNomeImpressora;
    public  TextView txtMac;
    public Activity activity;
    private ImpressorasFragment impFragment;

    public ViewHolderImpressora(@NonNull View itemView, List<Dipositivo> dispositivos, final Context context, Activity activity,ImpressorasFragment impFragment) {

        super(itemView);
        System.out.println("ConstrutoR");
        this.view               =      itemView;
        txtNomeImpressora       =      this.view.findViewById(R.id.txtNomeImpressora);
        txtMac                  =      this.view.findViewById(R.id.txtMac);
        this.impFragment = impFragment;
        if(txtNomeImpressora == null){
            System.out.println("txtNomeImpressora é null");
        }else{
            System.out.println("txtNomeImpressora foi achado");
        }


        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dispositivos.size() > 0) {


                        impFragment.progressDoalog = new ProgressDialog(view.getContext());
                        impFragment.progressDoalog.setMax(100);
                        impFragment.progressDoalog.setMessage("Aguarde....");
                        impFragment.progressDoalog.setIcon(R.drawable.ic_senha);
                        impFragment.progressDoalog.setTitle("Selecionando dispositivo!");
                        impFragment.progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        impFragment.progressDoalog.setIndeterminate(true);
                        impFragment.progressDoalog.show();


                    Dipositivo disp =dispositivos.get(getLayoutPosition());
                    System.out.println("DISPO: "+disp);
                    SessaoAplicacao.impressoraSelecionada = disp.getNome();
                    if(SessaoAplicacao.servicoImpressao == null){
                        SessaoAplicacao.servicoImpressao = new ServicoImpressao(activity);
                    }
                    if(ServicoImpressao.mmDevice == null){
                        SessaoAplicacao.servicoImpressao.findBT(disp.getNome());
                        try {
                            System.out.println("Imprimindo teste!");
                            SessaoAplicacao.servicoImpressao.openBT();
                            SessaoAplicacao.servicoImpressao.sendData("Conectado à "+disp.getNome()+"!\n\n\n\n");
                            SessaoAplicacao.servicoImpressao.closeBT();
                            Toast.makeText(context, disp.getNome()+" selecionada!", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    if(impFragment.progressDoalog.isShowing()){
                        impFragment.progressDoalog.dismiss();
                    }
                }
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Ações");
        MenuItem callItem = menu.add(0, v.getId(), 0, "Selecionar");//groupId, itemId, order, title
        callItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.out.println("HELLLLLLLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                return false;
            }
        });
    }






}