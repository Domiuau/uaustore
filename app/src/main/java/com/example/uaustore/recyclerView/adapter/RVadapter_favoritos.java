package com.example.uaustore.recyclerView.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uaustore.R;
import com.example.uaustore.models.ContaLogada;
import com.example.uaustore.models.Item;

import java.util.List;

public class RVadapter_favoritos extends RecyclerView.Adapter implements AdapterView.OnItemClickListener {

    List<Item> listaFav;
    Context context;
    static boolean fav1;

    private RVadapter_itens.OnItemClickListener listener;

    public RVadapter_favoritos(List<Item> lista_fav, Context context, boolean fav) {
        this.listaFav = lista_fav;
        this.context = context;
        fav1 = fav;
    }

    public void setListaFav(List<Item> lista_fav) {
        this.listaFav = lista_fav;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.itens_favoritos,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.vincula(listaFav.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemClick(holder.getAdapterPosition());
                    }
                }
            }
        });



    }

    public void setOnItemClickListener(RVadapter_itens.OnItemClickListener listener) {
        this.listener = listener;
    }


    public Item getItem (int id) {
        return listaFav.get(id);
    }

    @Override
    public int getItemCount() {
        return listaFav.size();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imagem;
        private final TextView nome;
        private final TextView precoNormal;
        private final TextView precoPromo;
        private final TextView porcentagemDesconto;
        private final TextView descricao;
        private final TextView quantidadeVendas;
        private final TextView quantidadeAvaliacoes;

        private final TextView starrate;
        private final ImageView menu;




        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.item_imagem);
            nome = itemView.findViewById(R.id.item_nome);
            precoNormal = itemView.findViewById(R.id.item_preco);
            precoPromo = itemView.findViewById(R.id.item_preco_promo);
            porcentagemDesconto = itemView.findViewById(R.id.porcentagem_desconto);
            descricao = itemView.findViewById(R.id.item_descricao);
            quantidadeAvaliacoes = itemView.findViewById(R.id.quantidade_avaliacoes);
            quantidadeVendas = itemView.findViewById(R.id.quantidade_vendas);
            starrate = itemView.findViewById(R.id.starrate);
            menu = itemView.findViewById(R.id.menu_fav);
        }

        public void vincula(Item item){

            imagem.setImageBitmap(item.getImagem_apresentacao());
            descricao.setText(item.getDescricao());
            starrate.setText(String.format("%.2f",item.getStarrate()));
            nome.setText(item.getNome());
            quantidadeAvaliacoes.setText("(" + (item.getQuantidadeAvaliacoes()-1)+")");
            quantidadeVendas.setText(item.getVendidos() + " vendidos");


            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context, view);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_fav, popupMenu.getMenu());
                    popupMenu.show();
                    listaFav.remove(item);
                    notifyDataSetChanged();

                    if (fav1)
                        ContaLogada.getContaLogada().setFavoritosRemover(item.getId());
                    else
                        ContaLogada.getContaLogada().setCarrinhoRemover(item.getId());
                }
            });


            if (item.getPreco_promo() > 0){
                precoNormal.setText("R$ " + item.getPreco());
                precoPromo.setText("R$ " + item.getPreco_promo());
                porcentagemDesconto.setText(String.format("%.2f", ((item.getPreco() - item.getPreco_promo())/item.getPreco())*100) + "%");
                precoNormal.setPaintFlags(precoNormal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            } else {

                precoPromo.setText("R$ " + item.getPreco().toString());

                ViewGroup.LayoutParams params = precoNormal.getLayoutParams();
                params.height = 0;

                precoNormal.setLayoutParams(params);
            }




        }
    }
}
