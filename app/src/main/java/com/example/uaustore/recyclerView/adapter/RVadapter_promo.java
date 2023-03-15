package com.example.uaustore.recyclerView.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uaustore.R;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;
import com.example.uaustore.ui.Inicio;
import com.example.uaustore.ui.Item_comprar;

import java.util.List;

public class RVadapter_promo extends RecyclerView.Adapter implements AdapterView.OnItemClickListener {

    private List<Item> listaPromo;
    private Context context;
    private RVadapter_itens.OnItemClickListener listener;
    private static String buscaFeita;
    private static int color;
    private static String[] precoDividido;

    public RVadapter_promo(List<Item> listaPromo, Context context) {
        this.listaPromo = listaPromo;
        this.context = context;
        color = Color.TRANSPARENT;
        buscaFeita = "";
    }

    public static void setColor(int color) {
        RVadapter_promo.color = color;
    }

    public RVadapter_promo(Context context) {
        this.context = context;
    }

    public void setListaPromo(List<Item> listaPromo, int cor, String texto) {
        this.listaPromo = listaPromo;
        notifyDataSetChanged();
        color = cor;
        buscaFeita = texto;
    }

    public void setOnItemClickListener(RVadapter_itens.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void remove(int i) {
        listaPromo.remove(i - 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.itens_promocao, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.vincular(listaPromo.get(position));

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
        return listaPromo.size();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public Item getItem(int id) {
        return listaPromo.get(id);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagem;
        private TextView nome;
        private TextView preco;
        private TextView starrate;
        private TextView preco_promo;
        private TextView precoVirgula;
        private TextView quantidadeVendidos;
        private TextView quantidadeAvaliacoes;
        private TextView porcentagemPromo;
        private ImageView favorito;
        private ImageView carrinho;
        Drawable drawable;
        InsetDrawable insetDrawable;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.imagem_item_promo);
            nome = itemView.findViewById(R.id.nome_item_promo);
            preco = itemView.findViewById(R.id.preco_item_normal);
            precoVirgula = itemView.findViewById(R.id.preco_item_normal1);
            starrate = itemView.findViewById(R.id.item_starrate_promo);
            preco_promo = itemView.findViewById(R.id.preco_item_promo);
            quantidadeVendidos = itemView.findViewById(R.id.item_quantidade_vendidos);
            quantidadeAvaliacoes = itemView.findViewById(R.id.item_quantidade_avaliacaoes);
            porcentagemPromo = itemView.findViewById(R.id.porcentagem_promo);
            favorito = itemView.findViewById(R.id.favorito);
            carrinho = itemView.findViewById(R.id.carrinho);
        }

        public void vincular(Item item) {

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


}


