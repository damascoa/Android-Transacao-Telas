package com.metre.projetotransacaotelas.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
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

import com.metre.adapter.ImpressoraAdapter;
import com.metre.adapter.ProdutoAdapter;
import com.metre.helper.Dipositivo;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.TemplateActivity;
import com.metre.projetotransacaotelas.subtelas.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class ImpressorasFragment extends Fragment {
    private RecyclerView lstImpressoras;
    BluetoothAdapter bluetoothAdapter;
    List<Dipositivo> dispositivos = new ArrayList<>();
    private ImpressoraAdapter impressoraAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_impressoras, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lstImpressoras = view.findViewById(R.id.lstImpressoras);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        lstImpressoras.setLayoutManager(manager);

        carregarDispositivosBluetooth(view);
        super.onViewCreated(view, savedInstanceState);
    }


    private final BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            Log.i("AÇÂO",action );
            if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                System.out.println("Finish");
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Dipositivo disp = new Dipositivo(device.getName(),device.getAddress(),device.getBondState()+"");
                dispositivos.add(disp);

                System.out.println("Device: "+disp);

                impressoraAdapter = new ImpressoraAdapter(dispositivos);
                lstImpressoras.setAdapter(impressoraAdapter);
            }
        }
    };

    @SuppressLint("MissingPermission")
    public void carregarDispositivosBluetooth(View view){
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            switch (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                case PackageManager.PERMISSION_DENIED:
                    if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    }
                    break;
                case PackageManager.PERMISSION_GRANTED:
                    break;
            }
        }


        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND); // kiedy znalezlismy
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        getActivity().registerReceiver(broadcastReceiver,intentFilter);



        bluetoothAdapter.startDiscovery();
    }
}
