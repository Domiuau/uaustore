package com.example.uaustore.models;

import android.graphics.Bitmap;
import android.widget.TextView;

public class CategoriaItem {

    private Bitmap imagemCategoria;
    private String nomeCategoria;

    public CategoriaItem(Bitmap imagemCategoria, String nomeCategoria)  {
        this.imagemCategoria = imagemCategoria;
        this.nomeCategoria = nomeCategoria;
    }

    public Bitmap getImagemCategoria() {
        return imagemCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
