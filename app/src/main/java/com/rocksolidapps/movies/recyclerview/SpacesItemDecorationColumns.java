package com.rocksolidapps.movies.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Skała
 */
public class SpacesItemDecorationColumns extends RecyclerView.ItemDecoration {
    private final int space;
    private final int columns;

    private boolean needLeftSpacing = false;

    public SpacesItemDecorationColumns(int space, int columns) {
        this.space = space;
        this.columns = columns;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int frameWidth = (int) ((parent.getWidth() - (float) space * (columns - 1)) / columns);
        int padding = parent.getWidth() / columns - frameWidth;
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
        if (itemPosition < columns) {
            outRect.top = 0;
        } else {
            outRect.top = space;
        }
        if (itemPosition % columns == 0) {
            outRect.left = 0;
            outRect.right = padding;
            needLeftSpacing = true;
        } else if ((itemPosition + 1) % columns == 0) {
            needLeftSpacing = false;
            outRect.right = 0;
            outRect.left = padding;
        } else if (needLeftSpacing) {
            needLeftSpacing = false;
            outRect.left = space - padding;
            if ((itemPosition + 2) % columns == 0) {
                outRect.right = space - padding;
            } else {
                outRect.right = space / 2;
            }
        } else if ((itemPosition + 2) % columns == 0) {
            needLeftSpacing = false;
            outRect.left = space / 2;
            outRect.right = space - padding;
        } else {
            needLeftSpacing = false;
            outRect.left = space / 2;
            outRect.right = space / 2;
        }
        outRect.bottom = 0;
    }
}