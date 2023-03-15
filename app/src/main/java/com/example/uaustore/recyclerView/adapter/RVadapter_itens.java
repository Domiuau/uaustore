package com.example.uaustore.recyclerView.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uaustore.R;
import com.example.uaustore.models.Conta;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;

import java.util.List;

public class RVadapter_itens extends RecyclerView.Adapter implements AdapterView.OnItemClickListener {

    private List<Item> listaItem;
    private Context context;
    private OnItemClickListener listener;
    private static String[] precoDividido;
    private static int color;
    private static String buscaFeita;
    ItemViewHolder itemViewHolder;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setListaItem(List<Item> lista_item, int cor, String texto) {
        this.listaItem = lista_item;
        color = cor;
        notifyDataSetChanged();
        buscaFeita = texto;
    }

    public static void setColor(int color) {
        RVadapter_itens.color = color;
    }


    public RVadapter_itens(List<Item> lista_item, Context context) {
        this.listaItem = lista_item;
        this.context = context;
        color = Color.TRANSPARENT;
        buscaFeita = "";

    }

    public RVadapter_itens(Context context) {
        this.context = context;

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.itens_itens, parent, false);

        return new RVadapter_itens.ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.vincula(listaItem.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (listener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }


            }
        });


    }


    @Override
    public int getItemCount() {
        return listaItem.size();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imagem;
        private final TextView nome;
        private final TextView preco;
        private final TextView preco_promo;
        private final TextView precoVirgula;
        private final TextView starrate;
        private final TextView quantidadeAvaliacoes;
        private final TextView quantidadeVendidos;
        private final TextView porcentagemPromo;
        private final ImageView favorito;
        private final ImageView carrinho;
        Drawable drawable;
        InsetDrawable insetDrawable;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.imagem_item_promo);
            nome = itemView.findViewById(R.id.nome_item_promo);
            preco = itemView.findViewById(R.id.preco_item_normal);
            preco_promo = itemView.findViewById(R.id.preco_item_promo);

            starrate = itemView.findViewById(R.id.item_starrate_promo);
            quantidadeAvaliacoes = itemView.findViewById(R.id.item_quantidade_avaliacaoes);
            quantidadeVendidos = itemView.findViewById(R.id.item_quantidade_vendidos);
            precoVirgula = itemView.findViewById(R.id.preco_item_normal1);
            porcentagemPromo = itemView.findViewById(R.id.porcentagem_promo);
            favorito = itemView.findViewById(R.id.favorito);
            carrinho = itemView.findViewById(R.id.carrinho);


        }


        @SuppressLint("ResourceAsColor")
        public void vincula(Item item) {

            if (item.getNome().equals(buscaFeita)) {
                drawable = new ColorDrawable(Color.GREEN);
            } else {
                drawable = new ColorDrawable(color);
            }


            drawable.setBounds(0, 0, nome.getWidth(), nome.getHeight());
            insetDrawable = new InsetDrawable(drawable, 0);
            nome.setBackground(insetDrawable);

            nome.setText(item.getNome());

            starrate.setText(String.format("%.1f", item.getStarrate()));
            quantidadeAvaliacoes.setText("(" + (item.getQuantidadeAvaliacoes() - 1) + ")");
            imagem.setImageBitmap(item.getImagem_apresentacao());
            quantidadeVendidos.setText(item.getVendidos() + " vendidos");
            if (item.getPreco_promo() == 0) {

                setPreco(String.format("%.2f", item.getPreco()));


            } else {

                porcentagemPromo.setText(String.format("%.1f", ((item.getPreco() - item.getPreco_promo()) / item.getPreco()) * 100) + "%");
                setPreco(String.format("%.2f", item.getPreco_promo()));
                preco_promo.setText(String.format("%.2f", item.getPreco()));
                preco_promo.setPaintFlags(preco.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

            if (ContaLogada.getContaLogada().getFavoritos().contains(item.getId() + ""))
                favorito.setVisibility(View.VISIBLE);


            if (ContaLogada.getContaLogada().getCarrinho().contains(item.getId() + ""))
                carrinho.setVisibility(View.VISIBLE);


        }

        private void setPreco(String precoItem) {

            precoDividido = precoItem.replace(".", ",").split(",");
            preco.setText(precoDividido[0]);
            precoVirgula.setText("," + precoDividido[1]);


        }

    }


    public Item getItem(int id) {
        return listaItem.get(id);
    }


}
