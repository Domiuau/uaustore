package com.example.uaustore.recyclerView.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uaustore.R;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RVadapter_anunciados extends RecyclerView.Adapter {

    List<Item> lista;
    Context context;

    public RVadapter_anunciados(List<Item> lista, Context context) {
        this.lista = lista;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itens_anunciados,parent,false);
        return new RVadapter_anunciados.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageView imagemAnunciado = holder.itemView.findViewById(R.id.imagem_anunciados);
        TextView nomeAnunciado = holder.itemView.findViewById(R.id.nome_anunciados);
        TextView categoriaAnunciado = holder.itemView.findViewById(R.id.categoria_anunciados);
        TextView precoAnunciadoPromo = holder.itemView.findViewById(R.id.preco_anunciados_promocao);
        TextView precoAnunciado = holder.itemView.findViewById(R.id.preco_anunciados);
        TextView dataAnunciado = holder.itemView.findViewById(R.id.data_anunciados);
        TextView descricaoAnunciado = holder.itemView.findViewById(R.id.descricao_anunciados);

        imagemAnunciado.setImageBitmap(lista.get(position).getImagem_apresentacao());
        nomeAnunciado.setText(lista.get(position).getNome());
        categoriaAnunciado.setText(lista.get(position).getCategoria());
        dataAnunciado.setText(lista.get(position).getDatadeanuncio());
        descricaoAnunciado.setText(lista.get(position).getDescricao());

        if (lista.get(position).getPreco_promo() > 0){

            precoAnunciadoPromo.setText(lista.get(position).getPreco()+"");
            precoAnunciado.setText(lista.get(position).getPreco_promo()+"");

        } else
            precoAnunciado.setText(lista.get(position).getPreco()+"");



    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

