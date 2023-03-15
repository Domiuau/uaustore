package com.example.uaustore.recyclerView.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uaustore.ui.perfil.Perfil_detalhesCompra;
import com.example.uaustore.R;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RVadapter_comprados extends RecyclerView.Adapter implements AdapterView.OnItemClickListener {

    private List<Item> lista;
    private List<String> listaCoisasConta = new ArrayList<>(Arrays.asList(ContaLogada.getContaLogada().getCompras_data().split("¬")));
    private Context context;
    private RVadapter_itens.OnItemClickListener listener;

    public RVadapter_comprados(List<Item> lista, Context context) {
        this.lista = lista;
        this.context = context;
        listaCoisasConta.remove(0);
    }

    public void setListener(RVadapter_itens.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public Item getItem(int position){
        return lista.get(position);
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itens_comprados,parent,false);
        return new RVadapter_comprados.ItemViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.vincula(lista.get(position),position);


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView imagemComprado;
        TextView nomeComprado;
        TextView categoriaComprado;
        TextView precoCompradoPromo;
        TextView precoComprado;
        TextView dataComprado;
        String[] string;
        Button avaliar;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imagemComprado = itemView.findViewById(R.id.imagem_comprado);
            nomeComprado = itemView.findViewById(R.id.nome_comprado);
            categoriaComprado = itemView.findViewById(R.id.categoria_comprado);
            precoCompradoPromo = itemView.findViewById(R.id.preco_comprado_promocao);
            precoComprado = itemView.findViewById(R.id.preco_comprado);
            dataComprado = itemView.findViewById(R.id.data_comprado);
            avaliar = itemView.findViewById(R.id.botao_avaliar);



        }

        public void vincula(Item item, int position){
            string = listaCoisasConta.get(position).split("@");

            imagemComprado.setImageBitmap(item.getImagem_apresentacao());
            nomeComprado.setText(item.getNome());

            categoriaComprado.setText(item.getCategoria());

            if (!string[0].equals("null")) {
                categoriaComprado.setText(item.getCategoria()  + " / " + string[0] + " estrelas");
            } else {
                categoriaComprado.setText(item.getCategoria() + " / não avaliado");
            }

            dataComprado.setText(string[1]);



            avaliar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContaLogada.setItem_comprar(item);
                    Intent intent = new Intent(context, Perfil_detalhesCompra.class);
                    intent.putExtra("position",position);
                    context.startActivity(intent);
                }
            });






            if (lista.get(position).getPreco_promo() > 0){
                precoComprado.setText(item.getPreco()+"");
                precoCompradoPromo.setText(item.getPreco_promo()+"");

            } else
                precoComprado.setText(item.getPreco()+"");


        }
    }
}
