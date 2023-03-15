package com.example.uaustore.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.uaustore.R;
import com.example.uaustore.models.Conta;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;
import com.example.uaustore.models.SliderItem;
import com.example.uaustore.recyclerView.adapter.RVadapter_itens;
import com.example.uaustore.recyclerView.adapter.RVadapter_promo;
import com.example.uaustore.recyclerView.adapter.RVadapter_slide;
import com.example.uaustore.recyclerView.itemDecoration.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class Item_comprar extends AppCompatActivity {

    ImageView estrela1, estrela2, estrela3, estrela4, estrela5;
    ImageView imagem_vendedor;
    TextView nome, starrate, quantidadeestoque, precopromocao, preco, descricao, vendedor, maisItensVendedor, maisItensCategoria, numeroVendas, quantidadeAvaliacoes, porcentagemDesconto, dataAnuncio;
    Button botao;
    ViewPager2 viewPager2;
    RecyclerView itensVendedor, itensCategoria;
    RVadapter_promo rVadapter_vendedor;
    RVadapter_itens rVadapter_categoria;
    Conta vendedor1;
    ConstraintLayout layout;
    Item item;
    ToggleButton favoritos, carrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_comprar);


        imagem_vendedor = findViewById(R.id.foto_vendedor);
        nome = findViewById(R.id.nomeproduto);
        starrate = findViewById(R.id.starrate);
        quantidadeestoque = findViewById(R.id.emestoque);
        precopromocao = findViewById(R.id.preco_promocao);
        preco = findViewById(R.id.preco);
        descricao = findViewById(R.id.descricao);
        vendedor = findViewById(R.id.nome_vendedor);
        botao = findViewById(R.id.comprar);
        numeroVendas = findViewById(R.id.numero_vendas);
        itensCategoria = findViewById(R.id.recycler_itens_categoria);
        itensVendedor = findViewById(R.id.recycler_itens_vendedor);
        maisItensVendedor = findViewById(R.id.mais_itens_vendedor);
        maisItensCategoria = findViewById(R.id.mais_itens_categoria);
        quantidadeAvaliacoes = findViewById(R.id.quantidade_avaliacoes);
        porcentagemDesconto = findViewById(R.id.porcentagem_desconto);
        estrela1 = findViewById(R.id.estrela1);
        estrela2 = findViewById(R.id.estrela2);
        estrela3 = findViewById(R.id.estrela3);
        estrela4 = findViewById(R.id.estrela4);
        estrela5 = findViewById(R.id.estrela5);
        dataAnuncio = findViewById(R.id.data_anuncio);
        viewPager2 = findViewById(R.id.imageSlide);
        carrinho = findViewById(R.id.carrinho);
        favoritos = findViewById(R.id.favoritos);
        item = ContaLogada.getItem_comprar();
        vendedor1 = Conta.getConta(item.getVendedorID(),this);

        if (ContaLogada.getContaLogada().getFavoritos().contains((item.getId()+""))){
            favoritos.setChecked(true);
        }


        if (ContaLogada.getContaLogada().getCarrinho().contains((item.getId()+"")))
            carrinho.setChecked(true);

        favoritos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b)
                    ContaLogada.getContaLogada().setFavoritos(item.getId());
                else
                    ContaLogada.getContaLogada().setFavoritosRemover(item.getId());


            }
        });

        carrinho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b)
                    ContaLogada.getContaLogada().setCarrinho(item.getId());
                else
                    ContaLogada.getContaLogada().setCarrinhoRemover(item.getId());
            }
        });


      //rVadapter_categoria = new RVadapter_itens(Item.getItens(this, item.getCategoria()),this);
        rVadapter_categoria = new RVadapter_itens(Item.getItensCat(this, item.getCategoria()),this);
        rVadapter_vendedor = new RVadapter_promo(Item.getItens(this, vendedor1.getAnunciados()), this);



        botao.setOnClickListener((View view) -> {

            ContaLogada.getContaLogada().comprar(item);

        });

        ImageView[] estrelas = {estrela1, estrela2, estrela3, estrela4, estrela5};

        int quantidadeestrela = 0;
        for (int i = 0; i < item.getStarrate(); i++){
            estrelas[i].setBackgroundResource(R.drawable.ic_estrela);
            quantidadeestrela = i;
        }

        if (item.getStarrate()%1 > 0)
            estrelas[quantidadeestrela].setBackgroundResource(R.drawable.ic_estrela_metade);






        itensVendedor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        itensCategoria.setLayoutManager(new GridLayoutManager(this,2));
        itensCategoria.addItemDecoration(new GridSpacingItemDecoration(12));
        itensVendedor.addItemDecoration(new GridSpacingItemDecoration(12));






        List<SliderItem> sliderItemList = new ArrayList<>();
        sliderItemList.add(new SliderItem(item.getImagem_apresentacao()));
        sliderItemList.add(new SliderItem(item.getImagens()[0]));
        sliderItemList.add(new SliderItem(item.getImagens()[1]));
        sliderItemList.add(new SliderItem(item.getImagens()[2]));

        RVadapter_slide rVadapter_slide = new RVadapter_slide(sliderItemList,viewPager2);

        viewPager2.setAdapter(rVadapter_slide);




        nome.setText(item.getNome());
        starrate.setText(String.format("%.1f",item.getStarrate()));
        quantidadeestoque.setText(item.getQuantidade() + " disponíveis");
        numeroVendas.setText(item.getVendidos() + " vendidos");
        quantidadeAvaliacoes.setText("(" + (item.getQuantidadeAvaliacoes()-1) + ")");

        dataAnuncio.setText(item.getDatadeanuncio());




        maisItensVendedor.setText(vendedor1.getNome());
        maisItensCategoria.setText(item.getCategoria());



        vendedor.setText(vendedor1.getNome());
        imagem_vendedor.setImageBitmap(vendedor1.getFoto_perfil());

        rVadapter_categoria.setOnItemClickListener((int position) -> {
            ContaLogada.setItem_comprar(rVadapter_categoria.getItem(position));
            startActivity(new Intent(Item_comprar.this, Item_comprar.class));

        });

        itensCategoria.setHasFixedSize(true);
        itensCategoria.setNestedScrollingEnabled(false);

        rVadapter_vendedor.setOnItemClickListener((int position) -> {
            ContaLogada.setItem_comprar(rVadapter_vendedor.getItem(position));
            startActivity(new Intent(Item_comprar.this, Item_comprar.class));

        });


        preco.setText(item.getPreco()+"");
        if (item.getPreco_promo() > 0){

            preco.setText("R$" + item.getPreco_promo().toString());
            precopromocao.setPaintFlags(precopromocao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            precopromocao.setText("R$" + item.getPreco().toString());
            porcentagemDesconto.setText(String.format("%.2f",((item.getPreco() - item.getPreco_promo())/item.getPreco())*100)+"%Off");
        } else {
            preco.setText("R$" + item.getPreco());
        }


        descricao.setText(item.getDescricao());

        itensCategoria.setAdapter(rVadapter_categoria);
        itensVendedor.setAdapter(rVadapter_vendedor);

        itensCategoria.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,((int) (270 * this.getResources().getDisplayMetrics().density + 0.5f) + 28) * (getItemListaSize())));






    }

    @Override
    protected void onResume() {
        super.onResume();





    }

    int getItemListaSize(){
        if (rVadapter_categoria.getItemCount()%2 == 0){

            return rVadapter_categoria.getItemCount()/2;
        }
        else {

            return (int) ((rVadapter_categoria.getItemCount()/2) + 1);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onBackPressed() {

        finish();
    }
}