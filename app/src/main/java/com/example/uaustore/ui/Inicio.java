package com.example.uaustore.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uaustore.Item_favoritos;
import com.example.uaustore.R;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;
import com.example.uaustore.recyclerView.adapter.RVadapter_itens;
import com.example.uaustore.recyclerView.adapter.RVadapter_promo;
import com.example.uaustore.recyclerView.itemDecoration.GridSpacingItemDecoration;
import com.example.uaustore.ui.perfil.Perfil_usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Inicio extends AppCompatActivity{

    RecyclerView recyclerView_promo, recyclerView_itens;
    RVadapter_promo rVadapter_promo;
    RVadapter_itens rVadapter_itens;
    List<Item> itensempromocao;
    List<Item> itens;
    LinearLayout layout;
    ImageButton anunciar, categorias, favoritos, carrinho, home;
    ScrollView scrollView;
    TextView textViewInicio, nomeUsuario;
    RelativeLayout layoutPerfil;
    ImageView fotoPerfil;
    EditText buscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        itensempromocao = new ArrayList<>();
        itens = new ArrayList<>();

        recyclerView_promo = findViewById(R.id.lista_promocoes);
        recyclerView_itens = findViewById(R.id.lista_itens);
        anunciar = findViewById(R.id.item_anunciar);
        categorias = findViewById(R.id.item_categorias);
        favoritos = findViewById(R.id.item_favorito);
        carrinho = findViewById(R.id.item_carrinho);
        home = findViewById(R.id.item_home);
        scrollView = findViewById(R.id.scrollview);
        textViewInicio = findViewById(R.id.textview_inicio);
        layoutPerfil = findViewById(R.id.layout_perfil);
        fotoPerfil = findViewById(R.id.imagem_perfil);
        nomeUsuario = findViewById(R.id.nome_usuario);
        buscar = findViewById(R.id.edittext_buscar);

        rVadapter_itens = new RVadapter_itens(this);
        rVadapter_promo = new RVadapter_promo(this);

        textViewInicio.setPaintFlags(textViewInicio.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        for (Item item1:
                Item.getItens(this)) {
            if (item1.getPreco_promo() > 0)
                itensempromocao.add(item1);
            else
                itens.add(item1);


        }


        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable editable) {

                buscarLista();

            }
        });




        fotoPerfil.setImageBitmap(ContaLogada.getContaLogada().getFoto_perfil());
        nomeUsuario.setText(ContaLogada.getContaLogada().getNome());

        layout = findViewById(R.id.container1);
        layoutPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Inicio.this, Perfil_usuario.class));
            }
        });


        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(Inicio.this, Item_favoritos.class));
                intent.putExtra("fav", true);
                startActivity(intent);
            }
        });

        carrinho.setOnClickListener((View view) -> {

            Intent intent = (new Intent(Inicio.this, Item_favoritos.class));
            intent.putExtra("fav", false);
            startActivity(intent);

        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.smoothScrollTo(0,0);

            }
        });

        anunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Inicio.this, Item_anunciar.class));
            }
        });

        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        recyclerView_promo.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView_itens.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView_itens.setHasFixedSize(true);
        recyclerView_itens.setNestedScrollingEnabled(false);
        recyclerView_itens.addItemDecoration(new GridSpacingItemDecoration(14));


        rVadapter_itens.setOnItemClickListener((int position) -> {
                ContaLogada.setItem_comprar(rVadapter_itens.getItem(position));
                startActivity(new Intent(Inicio.this, Item_comprar.class));

        });


        rVadapter_promo.setOnItemClickListener((int position) -> {
                ContaLogada.setItem_comprar(rVadapter_promo.getItem(position));
                startActivity(new Intent(Inicio.this, Item_comprar.class));

        });








        recyclerView_itens.setAdapter(rVadapter_itens);
        recyclerView_promo.setAdapter(rVadapter_promo);



    }

    public void buscarLista(){
       LinkedList<Item> listaBusca = new LinkedList<>();

        for (Item item1:
                itens) {
            if (item1.getNome().toLowerCase().contains(buscar.getText().toString().toLowerCase())){
                listaBusca.add(item1);
            }
        }

        rVadapter_itens.setListaItem(new LinkedList<>(listaBusca), !buscar.getText().toString().equals("") ? Color.YELLOW: Color.TRANSPARENT, buscar.getText().toString());
        listaBusca.clear();

        for (Item item1:
                itensempromocao) {
            if (item1.getNome().toLowerCase().contains(buscar.getText().toString().toLowerCase())){
                listaBusca.add(item1);
            }
        }

        rVadapter_promo.setListaPromo(new LinkedList<>(listaBusca), !buscar.getText().toString().equals("") ? Color.YELLOW: Color.TRANSPARENT, buscar.getText().toString());
        arrumarTamanhoTela();
    }

    int getItemListaSize(){
        if (rVadapter_itens.getItemCount()%2 == 0){

            return (rVadapter_itens.getItemCount()/2);
        }
        else {

            return (int) ((rVadapter_itens.getItemCount()/2) + 1);
        }




    }

    @Override
    protected void onResume() {
        super.onResume();


        buscarLista();



    }

    private void arrumarTamanhoTela(){
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(((int) (270 * this.getResources().getDisplayMetrics().density + 0.5f) + 28) * (getItemListaSize())) + (int) (50 * this.getResources().getDisplayMetrics().density + 0.5f)));
    }



}