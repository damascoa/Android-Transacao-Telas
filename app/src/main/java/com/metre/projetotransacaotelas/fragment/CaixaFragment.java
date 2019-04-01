package com.metre.projetotransacaotelas.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.metre.OUtil;
import com.metre.SessaoAplicacao;
import com.metre.adapter.CaixaAdapter;
import com.metre.config.RetrofitConfig;
import com.metre.lib.printer.thermal.Printer;
import com.metre.lib.printer.thermal.bluetooth.BluetoothPrinters;
import com.metre.model.Caixa;
import com.metre.model.CaixaItem;
import com.metre.model.FechamentoCaixa;
import com.metre.model.FechamentoCaixaDetalhe;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.subtelas.AbrirCaixa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaixaFragment extends Fragment {
    private TextView lblSaldo;
    private Button btnAbrir;
    private Button btnFechar;
    private RecyclerView lstMovimento;
    private FragmentActivity myContext;


    private ProgressDialog mProgressDialog;
    List<CaixaItem> movimento = new ArrayList<>();
    private CaixaAdapter caixaAdapter;


    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("onViewCreated");
        lblSaldo = view.findViewById(R.id.lblSaldo);
        btnAbrir = view.findViewById(R.id.btnAbrir);
        btnFechar = view.findViewById(R.id.btnFechar);
        lstMovimento = view.findViewById(R.id.lstMovimento);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        lstMovimento.setLayoutManager(manager);
        carregarSaldo();
        carregarMovimentoCaixa();

        if (SessaoAplicacao.caixa == null) {
            btnFechar.setEnabled(false);
            btnAbrir.setEnabled(true);
        } else {
            btnFechar.setEnabled(true);
            btnAbrir.setEnabled(false);
        }
        btnAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AbrirCaixa.class));
            }
        });

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharCaixa(v);
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        System.out.println("onCreateContextMenu");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void carregarSaldo() {
        System.out.println("carregarSaldo");
        if (SessaoAplicacao.usuario.getCaixaCorrente() != null) {
            Call<String> call = new RetrofitConfig().getCaixaService().saldo(SessaoAplicacao.usuario.getCaixaCorrente());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    System.out.println("CHAMOUUUUU " + response.body());
                    if (response.body() != null) {
                        lblSaldo.setText(response.body().toString());
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public void carregarMovimentoCaixa() {
        System.out.println("Carregando");
        movimento = new ArrayList<>();
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        try {
            System.out.println("ID DO CAIXA: " + SessaoAplicacao.usuario.getCaixaCorrente());
            if (SessaoAplicacao.caixa == null) {
                buscarCaixa(SessaoAplicacao.usuario.getCaixaCorrente());
                //btnAbrir.setVisibility(View.GONE);
                btnFechar.setVisibility(View.GONE);
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            } else {
                btnAbrir.setVisibility(View.GONE);
                //   btnFechar.setVisibility(View.GONE);
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }

            }

            Call<List<CaixaItem>> call = new RetrofitConfig().getCaixaService().buscarMovimento(SessaoAplicacao.usuario.getCaixaCorrente());
            call.enqueue(new Callback<List<CaixaItem>>() {
                @Override
                public void onResponse(Call<List<CaixaItem>> call, Response<List<CaixaItem>> response) {
                    if (response.body() != null) {
                        movimento.addAll(response.body());
                        lstMovimento.setAdapter(new CaixaAdapter(movimento));
                    } else {
                        System.out.println("NAO VEIO NADA ");
                    }
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<List<CaixaItem>> call, Throwable t) {
                    Log.e("Erro buscar o movimento", t.getMessage());
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                }
            });

        } catch (Exception e) {
            System.err.println("Erro ao buscar o movimento do caixa!" + e.getMessage());
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("onCreateView");
        return inflater.inflate(R.layout.fragment_caixa, container, false);

    }


    public void buscarCaixa(Integer id) {
        Call<Caixa> call = new RetrofitConfig().getCaixaService().buscarCaixa(id);
        call.enqueue(new Callback<Caixa>() {
            @Override
            public void onResponse(Call<Caixa> call, Response<Caixa> response) {
                if (response.body() != null) {
                    SessaoAplicacao.caixa = response.body();
                }
            }

            @Override
            public void onFailure(Call<Caixa> call, Throwable t) {
                Toast.makeText(getContext(), "Não estamos encontrando o caixa " + id + " no servidor!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fecharCaixa(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Fechamento de Caixa");
        builder.setMessage("Confirma o fechamento!");

        String positiveText = "Sim";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("CLICOU EM SIM");
                        mProgressDialog = new ProgressDialog(getContext());
                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Fechando caixa...");
                        mProgressDialog.setCanceledOnTouchOutside(false);
                        mProgressDialog.show();
                        Call<FechamentoCaixa> call = new RetrofitConfig().getCaixaService().fecharCaixa(SessaoAplicacao.caixa.getIdCaixa());
                        call.enqueue(new Callback<FechamentoCaixa>() {
                            @Override
                            public void onResponse(Call<FechamentoCaixa> call, Response<FechamentoCaixa> response) {
                                System.out.println(response.code());
                                System.out.println(response.body());
                                if (response.body() != null) {

                                    FechamentoCaixa fec = response.body();
                                    try {
                                                                                imprimirFechamento(fec);
                                    } catch (Exception e) {
                                        Toast.makeText(getContext(), "Impressora não está configurada!", Toast.LENGTH_LONG).show();
                                    }

                                    if (mProgressDialog.isShowing()) {
                                        mProgressDialog.dismiss();
                                    }

                                    SessaoAplicacao.usuario.setCaixaCorrente(null);
                                    SessaoAplicacao.caixa = null;
                                    lblSaldo.setText("R$ 0,00");

                                    FragmentManager fragManager = myContext.getSupportFragmentManager();
                                    fragManager.beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

                                }
                                if (mProgressDialog.isShowing()) {
                                    mProgressDialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<FechamentoCaixa> call, Throwable t) {
                                t.printStackTrace();
                                if (mProgressDialog.isShowing()) {
                                    mProgressDialog.dismiss();
                                }
                            }
                        });
                    }
                });

        String negativeText = "Não";
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("CLICOU EM NAO ");
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    private void imprimirFechamento(FechamentoCaixa fec) {
        try {


            Printer printer = new Printer(BluetoothPrinters.selectFirstPairedBluetoothPrinter(SessaoAplicacao.impressoraSelecionada), 300, 48f, 32);

            printer.printFormattedText("[C]\n    <font size='big'>Fechamento do Caixa</font>\n\n");
            printer.printFormattedText("[C]--------------------------------\n\n\n\n");
            printer.printFormattedText("[L]<b>Responsável: " + SessaoAplicacao.usuario.getUsuario() + "</b>\n");
            printer.printFormattedText("[C]--------------------------------\n\n");
            printer.printFormattedText("[L]<b>Entradas: " + OUtil.formatarMoeda2(new BigDecimal(fec.getEntradas())) + "\n");
            printer.printFormattedText("[L]<b>Saidas: " + OUtil.formatarMoeda2(new BigDecimal(fec.getSaidas())) + "\n");
            printer.printFormattedText("[L]<b>Saldo: " + OUtil.formatarMoeda2(new BigDecimal(fec.getEntradas()).subtract(new BigDecimal(fec.getSaidas()))) + "\n");
            printer.printFormattedText("[C]--------------------------------\n\n");
            printer.printFormattedText("[C]\n    <font size='big'>Detalhes</font>\n\n");
            for (FechamentoCaixaDetalhe f : fec.getDetalhes()) {
                System.out.println("Caixa DET: " + f);
                printer.printFormattedText("[L]" + f.getForma() + ": " + f.getTotal() + "\n");
            }
            printer.printFormattedText("[C]--------------------------------\n\n\n\n");
            printer.disconnectPrinter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
