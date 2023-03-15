package com.example.uaustore.ui.perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uaustore.R;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;
import com.example.uaustore.ui.Item_comprar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Perfil_detalhesCompra extends AppCompatActivity {

    TextView avaliacao, nomeProduto, dataDeCompra, precoAtual, statusAvaliacao, quantidadeEstrelas;
    ImageView estrela1, estrela2, estrela3, estrela4, estrela5, imagemProduto, imagemPaginaProduto;
    ImageButton confirmarAvaliacao;
    LinearLayout paginaProduto;
    int starrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_detalhes_compra);

        confirmarAvaliacao = findViewById(R.id.confirmar_avaliacao);
        estrela1 = findViewById(R.id.estrela1);
        estrela2 = findViewById(R.id.estrela2);
        estrela3 = findViewById(R.id.estrela3);
        estrela4 = findViewById(R.id.estrela4);
        estrela5 = findViewById(R.id.estrela5);
        avaliacao = findViewById(R.id.quantidade_estrelas);
        nomeProduto = findViewById(R.id.nome_produto);
        dataDeCompra = findViewById(R.id.data_de_compra);
        precoAtual = findViewById(R.id.preco_atual);
        imagemProduto = findViewById(R.id.imagem_produto);
        statusAvaliacao = findViewById(R.id.statusAvaliacao);
        quantidadeEstrelas = findViewById(R.id.quantidade_estrelas);
        paginaProduto = findViewById(R.id.pagina_produto);
        imagemPaginaProduto = findViewById(R.id.imagem_pagina_produto);

        int position = getIntent().getIntExtra("position",0);
        List<String> lista = new ArrayList<>(Arrays.asList(ContaLogada.getContaLogada().getCompras_data().split("¬")));
        String[] string = lista.get(position+1).split("@");
        Item item = Item.getItem(this, Integer.parseInt(ContaLogada.getContaLogada().getComprados().get(position+1)));

        paginaProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContaLogada.setItem_comprar(item);
                startActivity(new Intent(Perfil_detalhesCompra.this, Item_comprar.class));
            }
        });





        nomeProduto.setText(item.getNome());
        dataDeCompra.setText(string[1]);
        precoAtual.setText(item.getPreco()+"");

        imagemProduto.setImageBitmap(item.getImagem_apresentacao());
        imagemPaginaProduto.setImageBitmap(item.getImagem_apresentacao());



        if (string[0].equals("null")){

            estrela1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adicionarEstrelas(1);
                }
            });

            estrela2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adicionarEstrelas(2);
                }
            });

            estrela3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adicionarEstrelas(3);
                }
            });

            estrela4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adicionarEstrelas(4);
                }
            });

            estrela5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adicionarEstrelas(5);
                }
            });

            confirmarAvaliacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ContaLogada.getItem_comprar().setStarrate(Double.parseDouble(starrate+""));


                    lista.set(position+1, starrate + "@" + string[1]);



                    String dataAvaliacoes = "";
                    for (String s:
                            lista) {
                        dataAvaliacoes += s + "¬";
                    }
                    ContaLogada.getContaLogada().setCompras_data(dataAvaliacoes);
                    ContaLogada.getContaLogada().setSaldo(ContaLogada.getContaLogada().getSaldo()+0.50);
                    Toast.makeText(Perfil_detalhesCompra.this,"Obrigado por avaliar " + item.getNome() + "! R$ 0,50 foram adicionados à sua conta", Toast.LENGTH_SHORT).show();
                    ContaLogada.setItem_comprar(item);
                    Intent intent = new Intent(Perfil_detalhesCompra.this, Perfil_detalhesCompra.class);
                    intent.putExtra("position",position);
                    Perfil_detalhesCompra.this.startActivity(intent);
                    finish();

                }
            });

        } else {

            statusAvaliacao.setText("Produto já avaliado, com " + string[0] + " estrelas");
            adicionarEstrelas(Integer.parseInt(string[0]));
            confirmarAvaliacao.setBackgroundTintList(ColorStateList.valueOf(Perfil_detalhesCompra.this.getResources().getColor(R.color.cinzapadrao)));
            quantidadeEstrelas.setText(string[0]);



        }



    }

    private void adicionarEstrelas(int starrateInput){

        starrate = starrateInput;
        quantidadeEstrelas.setText(starrateInput+"");

        ImageView[] estrelas = {
                estrela1, estrela2, estrela3, estrela4, estrela5
        };

        for (int i = 0; i < estrelas.length; i++) {

            estrelas[i].setBackgroundResource(R.drawable.ic_estrela_vazia);

        }

        for (int i = 0; i < starrate; i++) {
            estrelas[i].setBackgroundResource(R.drawable.ic_estrela);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}