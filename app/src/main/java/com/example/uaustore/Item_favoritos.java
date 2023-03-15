package com.example.uaustore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uaustore.models.Conta;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;
import com.example.uaustore.recyclerView.adapter.RVadapter_favoritos;
import com.example.uaustore.recyclerView.adapter.RVadapter_itens;
import com.example.uaustore.ui.Item_comprar;

import java.util.List;

public class Item_favoritos extends AppCompatActivity {

    private RecyclerView recycler_fav;
    private RVadapter_favoritos rVadapter_favoritos;
    boolean fav;

    ImageButton buttonFav, buttonCarrinho;
    TextView textViewFav, textViewCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_favoritos);
        recycler_fav = findViewById(R.id.recycler_favoritos);
        buttonFav = findViewById(R.id.item_favorito);
        textViewFav = findViewById(R.id.textview_fav);
        buttonCarrinho = findViewById(R.id.item_carrinho);
        textViewCarrinho = findViewById(R.id.textview_carrinho);

        fav = getIntent().getBooleanExtra("fav", true);

        if (fav) {
            arrumarBarraDeBaixo(buttonFav, textViewFav, getResources().getColorStateList(R.color.vermelhomeiorosadoeuacho));
            iniciarAdapter(ContaLogada.getContaLogada().getFavoritos());

        } else {
            arrumarBarraDeBaixo(buttonCarrinho, textViewCarrinho, getResources().getColorStateList(R.color.amarelo));
            iniciarAdapter(ContaLogada.getContaLogada().getCarrinho());
        }





        recycler_fav.setAdapter(rVadapter_favoritos);
        recycler_fav.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        rVadapter_favoritos.setOnItemClickListener(new RVadapter_itens.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                System.out.println(rVadapter_favoritos.getItem(position).getId());
                ContaLogada.setItem_comprar(rVadapter_favoritos.getItem(position));
                Toast.makeText(Item_favoritos.this, rVadapter_favoritos.getItemCount()+"",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Item_favoritos.this, Item_comprar.class));
            }
        });


    }

    private void arrumarBarraDeBaixo(ImageButton imageButton, TextView textView, ColorStateList colorStateList1){

        Drawable drawable = imageButton.getDrawable();
        drawable.setAlpha(255);
        ColorStateList colorStateList = colorStateList1;
        drawable.setTintList(colorStateList);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);

    }

    private void iniciarAdapter(List<String> list){
        rVadapter_favoritos = new RVadapter_favoritos(Item.getItens(this,list),this, fav);
        System.out.println(Item.getItens(this,list));
    }
    @Override
    protected void onResume() {
        super.onResume();

//        if (fav)
//            iniciarAdapter(ContaLogada.getContaLogada().getFavoritos());
//        else
//            iniciarAdapter(ContaLogada.getContaLogada().getCarrinho());

//        recycler_fav.setAdapter(rVadapter_favoritos);
    }
}