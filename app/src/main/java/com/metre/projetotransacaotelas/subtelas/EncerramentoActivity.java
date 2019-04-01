package com.metre.projetotransacaotelas.subtelas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.metre.OUtil;
import com.metre.SessaoAplicacao;
import com.metre.adapter.ProdutoAdapter;
import com.metre.adapter.RecebimentoAdapter;
import com.metre.config.RetrofitConfig;
import com.metre.lib.printer.thermal.Printer;
import com.metre.lib.printer.thermal.bluetooth.BluetoothPrinters;
import com.metre.model.Administradora;
import com.metre.model.AdministradoraTipo;
import com.metre.model.Bandeira;
import com.metre.model.FormaQuitacao;
import com.metre.model.Pedido;
import com.metre.model.PedidoItem;
import com.metre.model.PedidoRecebimento;
import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.fragment.ImpressorasFragment;
import com.metre.projetotransacaotelas.fragment.PedidoFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EncerramentoActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private FragmentActivity myContext;
    private Pedido pedido;
    private Spinner spinner2;
    private TextView lblValorPedido,lblValorRecebido,lblValorRestante;
    private RecyclerView lstRecebimentos;
    private List<PedidoRecebimento> recebimentos = new ArrayList<>();
    RecebimentoAdapter recebimentoAdapter;
     ProgressDialog progressDoalog;

    FormaQuitacao formaSelecionada;
    Administradora administradoraSelecionada;
    String tipoCartao;
    Bandeira bandeiraSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encerramento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(spinner2 == null) {
            spinner2 = findViewById(R.id.spinner2);
            spinner2.setOnItemSelectedListener(this);
            System.out.println(" NULO");
        }else{
            System.out.println("SPINNER NAO É NULO");
        }
        carregarFormasQuitacao(this);
        carregarAdministradoras();

        lblValorPedido = findViewById(R.id.lblValorPedido);
        lblValorRecebido = findViewById(R.id.lblValorRecebido);
        lblValorRestante = findViewById(R.id.lblValorRestante);
        lstRecebimentos = findViewById(R.id.lstRecebimentos);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        lstRecebimentos.setLayoutManager(manager);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("pedido")){
            pedido = (Pedido) bundle.getSerializable("pedido");
            lblValorPedido.setText(OUtil.formatarMoeda2(pedido.getTotal()));
            lblValorRestante.setText(OUtil.formatarMoeda2(pedido.getTotal()));
        }

    }

    public void carregarFormas(BigDecimal valor){
        recebimentos.add(addRecebimento(valor));
        lblValorRecebido.setText(OUtil.formatarMoeda2(recebimentos.stream().map(m -> m.getValor()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)));
        lblValorRestante.setText(OUtil.formatarMoeda2(pedido.getTotal().subtract(recebimentos.stream().map(m -> m.getValor()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO))));

        recebimentoAdapter = new RecebimentoAdapter(recebimentos);
        lstRecebimentos.setAdapter(recebimentoAdapter);
    }

    public PedidoRecebimento addRecebimento(BigDecimal valor){
            System.out.println("ADD::::::::::::::::"+valor);

            PedidoRecebimento pr = new PedidoRecebimento();
            if(administradoraSelecionada != null) {
                pr.setIdAdministradora(administradoraSelecionada.getIdAdministradora());
            }
            if(bandeiraSelecionada!= null) {
                pr.setIdBandeira(bandeiraSelecionada.getIdBandeira());
                System.out.println("Administradora : "+administradoraSelecionada.getIdAdministradora());
                administradoraSelecionada.getAdministradoraTipo().forEach(p -> {
                    System.out.println("Tipo: "+ p.getTipo());
                    System.out.println("Admin Tipo: "+p.getIdAdministradoraTipo());
                });
                AdministradoraTipo at = administradoraSelecionada.getAdministradoraTipo().stream().filter(p -> p.getIdAdministradora() == administradoraSelecionada.getIdAdministradora() && p.getTipo().equals(tipoCartao)).findAny().get();
                System.out.println("Administradora Tipo Selecionada: "+at);
                pr.setIdAdministradoraTipo(at.getIdAdministradoraTipo());
            }
            pr.setIdFormaRecebimento(formaSelecionada.getIdFormaQuitacao());
            if(tipoCartao != null){
                pr.setForma(formaSelecionada.getDescricao()+" de "+ (tipoCartao.equals("C") ? "Crédito" : "Débito"));
            }else {
                pr.setForma(formaSelecionada.getDescricao());
            }
            pr.setValor(valor);
            System.out.println("RETURN+++"+pr.toString());
        return pr;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == (android.R.id.home)){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        if(position > 0 && view != null){
            FormaQuitacao item = (FormaQuitacao) parent.getItemAtPosition(position);
            formaSelecionada = item;
            if(item.getForma().equals("CC")){
                abrirDialogoCartao(view.getContext());
            }else{
                abrirDialogoValor(view.getContext());
            }
        }

    }


    static AlertDialog alert = null;
    LinearLayout.LayoutParams paramButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    public void abrirDialogoCartao(Context c){
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View promptView = layoutInflater.inflate(R.layout.dialogcartao, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
        alertDialogBuilder.setView(promptView);

        final Button btnCartaoCredito = promptView.findViewById(R.id.btnCartaoCredito);
        final Button btnCartaoDebito = promptView.findViewById(R.id.btnCartaoDebito);
        LinearLayout conteudoAdministradora = promptView.findViewById(R.id.conteudoAdministradora);
        LinearLayout conteudoBandeiras = promptView.findViewById(R.id.conteudoBandeiras);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 1, 0, 1);

        btnCartaoCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoCartao = "C";
                selecionarAdminBandeira(v, conteudoAdministradora, conteudoBandeiras, params);
            }
        });
        btnCartaoDebito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoCartao = "D";
                selecionarAdminBandeira(v, conteudoAdministradora, conteudoBandeiras, params);
            }
        });

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                limparVariaveis();
                                dialog.cancel();
                                spinner2.setSelection(0);
                            }
                        });

        // create an alert dialog
        alert = alertDialogBuilder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

    private void limparVariaveis() {
        formaSelecionada = null;
        administradoraSelecionada = null;
        bandeiraSelecionada = null;
        tipoCartao = "";
    }

    private void selecionarAdminBandeira(View v, LinearLayout conteudoAdministradora, LinearLayout conteudoBandeiras, LinearLayout.LayoutParams params) {
        conteudoAdministradora.removeAllViews();
        conteudoBandeiras.removeAllViews();
        for(Administradora a : SessaoAplicacao.administradorasCartao){
            administradoraSelecionada = a;
            Button b = new Button(v.getContext());
            b.setTextColor(Color.WHITE);
            b.setBackground(getDrawable(R.drawable.btn_round_grey));
            b.setText(a.getNome());
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    conteudoBandeiras.removeAllViews();
                    AdministradoraTipo at = administradoraSelecionada.getAdministradoraTipo().stream().filter( p -> p.getTipo().equals(tipoCartao) && p.getIdAdministradora().equals(administradoraSelecionada.getIdAdministradora())).findAny().get();
                    for(Bandeira band : at.getBandeiras()){
                        Button ba = new Button(v.getContext());
                        ba.setBackground(getDrawable(R.drawable.btn_round_primary));
                        ba.setText(band.getNome());
                        ba.setTextColor(Color.WHITE);
                        ba.setLayoutParams(params);
                        ba.setPadding(0,1,0,1);
                        ba.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bandeiraSelecionada = band;
                                abrirDialogoValor(v.getContext());
                                alert.hide();
                            }
                        });
                        conteudoBandeiras.addView(ba,paramButton);
                    }
                }
            });
            conteudoAdministradora.addView(b,paramButton);
        }
    }


    static AlertDialog alertValor;
    public void abrirDialogoValor(Context c){
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View promptView = layoutInflater.inflate(R.layout.dialogrecebimento, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
        alertDialogBuilder.setView(promptView);



        AppCompatEditText iptValorRecebimento = promptView.findViewById(R.id.iptValorRecebimento);
        iptValorRecebimento.setText(lblValorRestante.getText().toString().replace(".","").replace(",","."));
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(!iptValorRecebimento.getText().toString().isEmpty()){
                            System.out.println("VALOR::::::::::::"+iptValorRecebimento.getText().toString());
                            carregarFormas(new BigDecimal(iptValorRecebimento.getText().toString()));
                            limparVariaveis();
                            spinner2.setSelection(0);
                            System.out.println("RECEBER");
                        }
                    }
                })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                limparVariaveis();
                                spinner2.setSelection(0);
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        alertValor = alertDialogBuilder.create();
        alertValor.show();
    }





    private void carregarFormasQuitacao(Context c){
        if(SessaoAplicacao.formasQuitacao == null){
            Call<List<FormaQuitacao>> callForma = new RetrofitConfig().getFormaService().listarFormas();
            callForma.enqueue(new Callback<List<FormaQuitacao>>() {
                @Override
                public void onResponse(Call<List<FormaQuitacao>> call, Response<List<FormaQuitacao>> response) {
                    if(response.body() != null){
                        SessaoAplicacao.formasQuitacao = new ArrayList<>();
                        SessaoAplicacao.formasQuitacao.add(new FormaQuitacao("Selecione a forma!"));
                        SessaoAplicacao.formasQuitacao.addAll( response.body());
                        criarDropBoxFormasPagamento(c);
                    }
                }
                @Override
                public void onFailure(Call<List<FormaQuitacao>> call, Throwable t) {
                    Toast.makeText(EncerramentoActivity.this, "Erro ao buscar as formas de quitação! "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            criarDropBoxFormasPagamento(c);
        }
    }

    private void criarDropBoxFormasPagamento(Context c) {
        ArrayAdapter<FormaQuitacao> dataAdapter = new ArrayAdapter<FormaQuitacao>(c, android.R.layout.simple_spinner_item, SessaoAplicacao.formasQuitacao){
            @Override
            public boolean isEnabled(int position) {
                if(position == 0){
                    return false;
                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Deixa o hint com a cor cinza ( efeito de desabilitado)
                    tv.setTextColor(Color.GRAY);
                }else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    private void carregarAdministradoras(){
        if(SessaoAplicacao.administradorasCartao == null){
            Call<List<Administradora>> callForma = new RetrofitConfig().getCartaoService().listarAdministradora();
            callForma.enqueue(new Callback<List<Administradora>>() {
                @Override
                public void onResponse(Call<List<Administradora>> call, Response<List<Administradora>> response) {
                    if(response.body() != null){
                        SessaoAplicacao.administradorasCartao = response.body();
                    }
                }
                @Override
                public void onFailure(Call<List<Administradora>> call, Throwable t) {
                    Toast.makeText(EncerramentoActivity.this, "Erro ao buscar as administradoras! "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void registrarPedido(View v){
        if(SessaoAplicacao.impressoraSelecionada == null || SessaoAplicacao.impressoraSelecionada.isEmpty()){
            Toast.makeText(this, "Selecione uma impressora!", Toast.LENGTH_LONG).show();
            return;
        }
        if(recebimentos.size() == 0){
            Toast.makeText(this, "Informe o recebimento", Toast.LENGTH_SHORT).show();
            return;
        }
        if(new BigDecimal(lblValorRestante.getText().toString().replace(".","").replace(",",".").trim()).doubleValue() > 0){
            Toast.makeText(this, "Falta receber o valor de "+lblValorRestante.getText().toString()+"!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDoalog = new ProgressDialog(v.getContext());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Aguarde....");
        progressDoalog.setTitle("Registrando o pedido");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setIndeterminate(true);
        // show it
        progressDoalog.show();
        pedido.setRecebimentos(recebimentos);
        Call<Pedido> call = new RetrofitConfig().getPedidoService().registrarPedido(pedido);
        call.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if(response.body() != null){
                    Pedido p = response.body();
                    try {
                        imprimirVias(p);
                        pedido = new Pedido();
                        recebimentos = new ArrayList<>();
                        SessaoAplicacao.itensPedido = new ArrayList<>();
                        Toast.makeText(EncerramentoActivity.this, "Pedido registrado!", Toast.LENGTH_SHORT).show();
                        finishAfterTransition();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(EncerramentoActivity.this, "Sistema não conseguiu imprimir as fichas! Tente novamente.", Toast.LENGTH_LONG).show();
                    }


                }else {
                    System.out.println("Erro");
                }
                if(progressDoalog.isShowing()){
                    progressDoalog.dismiss();
                }

            }



            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                System.out.println("FAILURE");
                System.out.println(t.getMessage());
                if(progressDoalog.isShowing()){
                    progressDoalog.dismiss();
                }
            }
        });
    }

    private void imprimirVias(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome da Empresa\n\n");

        Printer printer = new Printer(BluetoothPrinters.selectFirstPairedBluetoothPrinter(SessaoAplicacao.impressoraSelecionada), 300, 48f, 32);

        for(PedidoItem i : pedido.getItens()){
            String codigo = "7"+OUtil.completaComZerosEsquerda(i.getIdPedidoItem()+"",9)+"000";

            printer.printFormattedText("[C]\n    <font size='big'>Ficha N° "+i.getIdPedidoItem().toString()+"</font>\n\n\n");
            printer.printFormattedText("[C]--------------------------------\n\n\n\n");
            printer.printFormattedText("[L]<b>"+i.getQuantidade()+" x "+i.getNomeProduto()+"</b>\n");
            printer.printFormattedText("[C]--------------------------------\n\n\n\n");
            printer.printFormattedText("[L][R]Preço: R$"+OUtil.formatarMoeda2(i.getPreco())+"");
            printer.printFormattedText("[L][R]<b>Total: R$"+OUtil.formatarMoeda2(i.getTotal())+"</b>\n");
            printer.printFormattedText("[C]<barcode type='ean13' height='10'>"+codigo+"</barcode>\n"+"[C]<b>"+codigo+"</b>\n\n\n");
            printer.printFormattedText("[C]--------------------------------\n\n\n\n");
        }
        printer.disconnectPrinter();

    }


    private String leftRightAlign(String str1, String str2) {
        String ans = str1 +str2;
        if(ans.length() <31){
            int n = (31 - str1.length() + str2.length());
            ans = str1 + new String(new char[n]).replace("\0", " ") + str2;
        }
        return ans;
    }

    private File pdfFile;



    public static byte[] getBytesFromFile(File file) throws IOException {
        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                    && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        return bytes;
    }


}
