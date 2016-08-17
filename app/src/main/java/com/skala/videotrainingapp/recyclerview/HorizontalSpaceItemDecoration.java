package com.skala.videotrainingapp.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Skała
 */
public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int space;

    public HorizontalSpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = 0;
        outRect.bottom = 0;
        outRect.right = 0;

        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
        if (itemPosition < 1) {
            outRect.left = 0;
        } else {
            outRect.left = space;
        }
    }
}
