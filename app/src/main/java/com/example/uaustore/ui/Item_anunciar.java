package com.example.uaustore.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uaustore.R;
import com.example.uaustore.models.CategoriaItem;
import com.example.uaustore.models.Conta;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;
import com.example.uaustore.recyclerView.adapter.RVadapter_categorias;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Item_anunciar extends AppCompatActivity {

    Button anunciar, anuncia;
    EditText nome, preco, preco_promo,  descricao, quantidade;
    ImageView imagem_apresentacao, imagem1, imagem2, imagem3, imagem_selecionada, imagem_categoria;
    RelativeLayout categoria;
    LinearLayout selecionar_categoria;
    Bitmap bitmap;
    Bitmap[] imagens = new Bitmap[4];
    private static final int PICK_IMAGE = 100;
    private static final int RESULT_OK = -1;
    int lugar_array;
    RecyclerView recyclerCategoria;
    TextView nome_categoria;
    List<CategoriaItem> listaCategorias = new ArrayList<>();
    EditText buscarItem;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_anunciar);

        anunciar = findViewById(R.id.anunciar);
        anuncia = findViewById(R.id.anunciar_teste);
        nome = findViewById(R.id.nome);
        preco = findViewById(R.id.preco_normal);
        preco_promo = findViewById(R.id.preco_promo);
        categoria = findViewById(R.id.categoria);
        quantidade = findViewById(R.id.quantidade);
        descricao = findViewById(R.id.descricao);
        imagem_apresentacao = findViewById(R.id.imagem_apresentacao);
        imagem1 = findViewById(R.id.imagem1);
        imagem2 = findViewById(R.id.imagem2);
        imagem3 = findViewById(R.id.imagem3);
        recyclerCategoria = findViewById(R.id.recycler_categorias);
        buscarItem = findViewById(R.id.edittext_buscar);
        imagem_categoria = findViewById(R.id.imagem_categoria);
        nome_categoria = findViewById(R.id.nome_categoria);
        selecionar_categoria = findViewById(R.id.selecionar_categoria);
        scrollView = findViewById(R.id.scrollView1);

        RVadapter_categorias rVadapter_categorias = new RVadapter_categorias(this);

        recyclerCategoria.setAdapter(rVadapter_categorias);
        recyclerCategoria.setLayoutManager(new GridLayoutManager(this,3,RecyclerView.HORIZONTAL,false));

        anuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Conta conta = ContaLogada.getContaLogada();

                CategoriaItem[] imagen = { new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_tecnologia),256, 256, true),"Tecnologia"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_esportes),256, 256, true),"Esportes"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_saude),256, 256, true),"Saude"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_veiculos),256, 256, true),"Veiculos"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_brinquedo),256, 256, true),"Brinquedos"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_limpeza),256, 256, true),"Limpeza"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_alimentacao),256, 256, true),"Alimentacão"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_ferramenta),256, 256, true),"Ferramentas"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_artes),256, 256, true),"Artes"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_instrumentos),256, 256, true),"Instrumentos"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_pets),256, 256, true),"Pets"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_turismo),256, 256, true),"Turismo"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_escritorio),256, 256, true),"Escritório"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_limpeza),256, 256, true),"Limpeza"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_uniformetrabalho),256, 256, true),"Uniforme trabalho"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_festa),256, 256, true),"Festa"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_audioevideos),256, 256, true),"Audio e Video"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_acampamento),256, 256, true),"Acampamento"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_construcao),256, 256, true),"Construção"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_seguranca),256, 256, true),"Segurança"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_joias),256, 256, true),"Joias"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_lanterna),256, 256, true),"Lanterna"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_piscina),256, 256, true),"Piscina"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_pesca),256, 256, true),"Pesca"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_higiene),256, 256, true),"Higiene"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_escolar),256, 256, true),"Escolar"),
                       new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Item_anunciar.this.getResources(), R.drawable.cat_montanhismo),256, 256, true),"Montanhismo")


                };

                Random random = new Random();

                for (int i = 0; i < imagen.length; i++) {

                    new Item(imagen[i].getNomeCategoria() + " TESTE", 120.0 + (i*10),110.56, imagen[i].getNomeCategoria(),imagen[i].getImagemCategoria(),
                            imagen[random.nextInt(27)].getImagemCategoria(),
                            imagen[random.nextInt(27)].getImagemCategoria(),
                            imagen[random.nextInt(27)].getImagemCategoria(),
                            "ITEM DA CATEGORIA " + imagen[i].getNomeCategoria() + " CRIADO PARA TESTE " + "ITEM DA CATEGORIA " + imagen[i].getNomeCategoria() + " CRIADO PARA TESTE " + "ITEM DA CATEGORIA " + imagen[i].getNomeCategoria() + " CRIADO PARA TESTE ", 456+i, Item_anunciar.this);

                    new Item(imagen[i].getNomeCategoria() + " TESTE", 120.0 + (i*10),0.0, imagen[i].getNomeCategoria(),imagen[i].getImagemCategoria(),
                            imagen[random.nextInt(27)].getImagemCategoria(),
                            imagen[random.nextInt(27)].getImagemCategoria(),
                            imagen[random.nextInt(27)].getImagemCategoria(),
                            "ITEM DA CATEGORIA " + imagen[i].getNomeCategoria() + " CRIADO PARA TESTE " + "ITEM DA CATEGORIA " + imagen[i].getNomeCategoria() + " CRIADO PARA TESTE " + "ITEM DA CATEGORIA " + imagen[i].getNomeCategoria() + " CRIADO PARA TESTE ", 456+i, Item_anunciar.this);
                }









            }
        });

        categoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selecionar_categoria.getVisibility() == View.GONE){

                    selecionar_categoria.setVisibility(View.VISIBLE);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });


                } else
                    selecionar_categoria.setVisibility(View.GONE);


            }
        });


        buscarItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                rVadapter_categorias.buscarItem(buscarItem.getText().toString());

            }
        });

        rVadapter_categorias.setOnItemClickListener(new RVadapter_categorias.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                imagem_categoria.setImageBitmap(rVadapter_categorias.getitem(position).getImagemCategoria());
                nome_categoria.setText(rVadapter_categorias.getitem(position).getNomeCategoria());
                selecionar_categoria.setVisibility(View.GONE);
            }
        });


        anunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                new Item(nome.getText().toString(),
                        Double.parseDouble(preco.getText().toString()),Double.parseDouble(preco_promo.getText().toString()), nome_categoria.getText().toString(),
                        imagens[0], imagens[1], imagens[2], imagens[3], descricao.getText().toString(), Integer.parseInt(quantidade.getText().toString()),Item_anunciar.this);





            }
        });

        imagem_apresentacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem_selecionada = imagem_apresentacao;
                lugar_array = 0;
                selectImage();

            }
        });

        imagem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem_selecionada = imagem1;
                lugar_array = 1;
                selectImage();

            }
        });

        imagem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem_selecionada = imagem2;
                lugar_array = 2;
                selectImage();
            }
        });

        imagem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagem_selecionada = imagem3;
                lugar_array = 3;
                selectImage();

            }
        });


    }


    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Bitmap arrumarfoto = Bitmap.createScaledBitmap(bitmap,256,256, true);

                imagem_selecionada.setImageBitmap(arrumarfoto);
                imagens[lugar_array] = arrumarfoto;

        imagem1.getLayoutParams().height = imagem1.getMeasuredWidth();
        imagem2.getLayoutParams().height = imagem2.getMeasuredWidth();
        imagem3.getLayoutParams().height = imagem3.getMeasuredWidth();
        imagem_apresentacao.getLayoutParams().height = imagem_apresentacao.getMeasuredWidth();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



    Bitmap getBitmap (Bitmap bitmap){
        return bitmap;
    }
}