package com.example.uaustore.recyclerView.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uaustore.R;
import com.example.uaustore.models.CategoriaItem;

import java.util.ArrayList;
import java.util.List;

public class RVadapter_categorias extends RecyclerView.Adapter implements AdapterView.OnItemClickListener {

    private List<CategoriaItem> listaCategoriasFiltro  = new ArrayList<>();
    private static List<CategoriaItem> listaCategorias = new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;

    public RVadapter_categorias(Context context) {
        this.context = context;

        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_tecnologia), 350, 350, true), "Tecnologia"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_moda), 350, 350, true), "Moda"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_beleza), 350, 350, true), "Beleza"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_esportes), 350, 350, true), "Esporte"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_saude), 350, 350, true), "Saúde"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_veiculos), 350, 350, true), "Veículos"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_brinquedo), 350, 350, true), "Brinquedos"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_livros), 350, 350, true), "Livros"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_alimentacao), 350, 350, true), "Alimentação"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_ferramenta), 350, 350, true), "Ferramentas"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_artes), 350, 350, true), "Arte e Artesanato"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_instrumentos), 350, 350, true), "Instrumentos"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_pets), 350, 350, true), "Pets"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_turismo), 350, 350, true), "Viagens"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_escritorio), 350, 350, true), "Escritório"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_agricultura), 350, 350, true), "Agricultura"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_limpeza), 350, 350, true), "Limpeza"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_uniformetrabalho), 350, 350, true), "Trajes de trabalho"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_festa), 350, 350, true), "Festa e eventos"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_audioevideos), 350, 350, true), "Áudio e videos"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_acampamento), 350, 350, true), "Acampamento"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_construcao), 350, 350, true), "Materiais de construção"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_seguranca), 350, 350, true), "Segurança"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_joias), 350, 350, true), "Joias"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_lanterna), 350, 350, true), "Iluminação"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_piscina), 350, 350, true), "Piscina"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_pesca), 350, 350, true), "Pesca"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_higiene), 350, 350, true), "Higiene"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_escolar), 350, 350, true), "Material escolar"));
        listaCategorias.add(new CategoriaItem(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_montanhismo), 350, 350, true), "Montanhismo"));








        buscarItem("");


    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CategoriaItem getitem(int position){
        return listaCategoriasFiltro.get(position);
    }

    public void buscarItem(String categoria){

        List<CategoriaItem> listaFiltrada = new ArrayList<>();
        listaFiltrada.clear();

        for (CategoriaItem categoriaItem:
             listaCategorias) {
            if (categoriaItem.getNomeCategoria().toLowerCase().contains(categoria.toLowerCase()))
                listaFiltrada.add(categoriaItem);

        }

        listaCategoriasFiltro = new ArrayList<>(listaFiltrada);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categoria,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.vincula(listaCategoriasFiltro.get(position));

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
        return listaCategoriasFiltro.size();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imagemCategoria;
        private final TextView nomeCategoria;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imagemCategoria = itemView.findViewById(R.id.imagem_categoria);
            nomeCategoria = itemView.findViewById(R.id.nome_categoria);


        }

        public void vincula(CategoriaItem categoriaItem){

            imagemCategoria.setImageBitmap(categoriaItem.getImagemCategoria());
            nomeCategoria.setText(categoriaItem.getNomeCategoria());


        }
    }
}
