package com.example.uaustore.recyclerView.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.uaustore.R;
import com.example.uaustore.models.SliderItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


public class RVadapter_slide extends RecyclerView.Adapter<RVadapter_slide.SliderViewHolder> {

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;


    public RVadapter_slide(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;

    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.itens_imagens_comprar,parent,false)
        );
    }



    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position),position);



    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }


    class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView posicao;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagem);
            posicao = itemView.findViewById(R.id.posicao);
        }

        private void setImage(SliderItem sliderItem, int position){
            imageView.setImageBitmap(sliderItem.getImage());
            posicao.setText((position+1) + " / 4");

        }

    }
}
