package com.example.uaustore.models;

import android.graphics.Bitmap;
import android.widget.TextView;

public class SliderItem {

    private Bitmap image;


    public SliderItem(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }


}
