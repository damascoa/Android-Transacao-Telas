package com.metre.projetotransacaotelas.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.metre.projetotransacaotelas.R;
import com.metre.projetotransacaotelas.subtelas.GrupoActivity;

import java.lang.reflect.Field;

public class PedidoFragment extends Fragment {
    private LinearLayout conteudo;
    LinearLayout.LayoutParams paramButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("=====================onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("=====================onCreateView");
        return inflater.inflate(R.layout.fragment_pedido, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("=====================onViewCreated");
        conteudo = getView().findViewById(R.id.conteudo);




        for(int i=0; i<20;i++){
            Button b = new Button(view.getContext());
            b.setText("Teste");
          //  int resID = getResources().getIdentifier("c"+i , "drawable", "android");

            Drawable image = getContext().getResources().getDrawable(R.drawable.c1);

            int h = image.getIntrinsicHeight();
            int w = image.getIntrinsicWidth();
            image.setBounds( 0, 0, w, h );
            b.setCompoundDrawables(image,null,null,null);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getContext(), GrupoActivity.class);
                    it.putExtra("grupo","grupo");
                    ((AppCompatActivity) getContext()).startActivityForResult(it, 0);
                }
            });
            conteudo.addView(b,paramButton);
        }


    }


}
