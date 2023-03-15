package com.example.uaustore.recyclerView.itemDecoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    public GridSpacingItemDecoration(int spacing) {
        this.spacing = spacing;
    }



    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = spacing;
        outRect.left = spacing;
        outRect.bottom = 14;
        outRect.top = 14;
    }


}
