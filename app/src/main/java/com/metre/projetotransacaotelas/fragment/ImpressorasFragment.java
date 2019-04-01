package com.metre.projetotransacaotelas.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.metre.SessaoAplicacao;
import com.metre.adapter.ImpressoraAdapter;
import com.metre.adapter.ProdutoAdapter;
import com.metre.helper.Dipositivo;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.TemplateActivity;
import com.metre.projetotransacaotelas.subtelas.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ImpressorasFragment extends Fragment {
    private RecyclerView lstImpressoras;
    private ImpressoraAdapter impressoraAdapter;
    private Button btnBuscarBluetooth;
    private TextView txtImpressoraSel;
    public  ImpressorasFragment obj;

    BluetoothAdapter bluetoothAdapter;
    public ProgressDialog progressDoalog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_impressoras, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lstImpressoras = view.findViewById(R.id.lstImpressoras);
        lstImpressoras.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.obj = this;

        btnBuscarBluetooth = view.findViewById(R.id.btnBuscarBluetooth);
        txtImpressoraSel = view.findViewById(R.id.txtImpressoraSel);

        super.onViewCreated(view, savedInstanceState);

        if(SessaoAplicacao.impressoraSelecionada != null){
            txtImpressoraSel.setText(SessaoAplicacao.impressoraSelecionada);
        }

        btnBuscarBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(progressDoalog == null ){
                    progressDoalog = new ProgressDialog(view.getContext());
                    progressDoalog.setMax(100);
                    progressDoalog.setMessage("Aguarde....");
                    progressDoalog.setIcon(R.drawable.ic_senha);
                    progressDoalog.setTitle("Buscando dispositivos!");
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDoalog.setIndeterminate(true);
                    progressDoalog.show();
                }


                listarDispositivos();
                impressoraAdapter = new ImpressoraAdapter(SessaoAplicacao.dispositivos, obj);

                lstImpressoras.setAdapter(impressoraAdapter);
                if(progressDoalog.isShowing()){
                    progressDoalog.dismiss();
                }

            }
        });
    }




    private  BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            Log.i("AÇÂO",action );
            if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                System.out.println("Finish");
                //listarDispositivos();

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)){

                BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name=device.getName();
                String address=device.getAddress();
                String rssi=Integer.toString(intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE));
                String deviceString= "";
                if (name != null && !name.equals("")){
                    Dipositivo d = new Dipositivo(name,address,device.getBondState()+"");
                    System.out.println("Dispositivo: "+d.toString());
                    SessaoAplicacao.dispositivos = new ArrayList<>();
                    System.out.println("ADD::::");
                    SessaoAplicacao.dispositivos.add(d);
                }



            }
        }
    };


    public void listarDispositivos(){
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(this.getContext(), "Bluetooth não suportado!", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (!bluetoothAdapter.isEnabled()) {
                // Bluetooth is not enable :)
                Toast.makeText(this.getContext(), "Ative o bluetooth!", Toast.LENGTH_LONG).show();
                return;
            }
        }

        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND); // kiedy znalezlismy
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.getActivity().registerReceiver(broadcastReceiver,intentFilter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            switch (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                case PackageManager.PERMISSION_DENIED:
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this.getActivity(),
                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    }
                    break;
                case PackageManager.PERMISSION_GRANTED:
                    break;
            }
        }
        bluetoothAdapter.startDiscovery();


    }






}
