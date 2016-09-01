package com.rocksolidapps.movies.moviedescription;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rocksolidapps.movies.home.HomeUi;
import com.rocksolidapps.movies.image.BitmapTarget;

import java.lang.ref.WeakReference;

/**
 * @author Skała
 */
public class LoadBitmapFillPalette implements BitmapTarget {
    private final static float FACTOR_DARKER = 0.8f;
    private final static int HSV_TABLE = 3;

    private final WeakReference<ImageView> imageTarget;
    private final WeakReference<LinearLayout> container;
    private final WeakReference<HomeUi> homeUi;

    public LoadBitmapFillPalette(ImageView imageTarget, LinearLayout container, HomeUi homeUi) {
        this.imageTarget = new WeakReference<>(imageTarget);
        this.container = new WeakReference<>(container);
        this.homeUi = new WeakReference<>(homeUi);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap) {
        ImageView imageView = imageTarget.get();
        if (imageView == null) {
            return;
        }

        imageView.setImageBitmap(bitmap);
        Palette.from(bitmap).generate(this::setColorFromPalette);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        // ignored
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        // ignored
    }

    private void setColorFromPalette(Palette palette) {
        HomeUi homeUi = this.homeUi.get();
        LinearLayout container = this.container.get();
        if (homeUi == null || container == null) {
            return;
        }

        Palette.Swatch swatch = palette.getVibrantSwatch();
        if (swatch == null) {
            swatch = palette.getMutedSwatch();
        }
        if (swatch != null) {
            int primaryColor = swatch.getRgb();
            int primaryDarkColor = getDarkerColor(primaryColor);
            int textColor = swatch.getTitleTextColor();

            homeUi.updateToolbarColor(primaryColor, primaryDarkColor);
            setTextColor(container, textColor);
            setColorBackground(container, primaryColor);
        }
    }

    private void setTextColor(LinearLayout linearLayout, int textColor) {
        int size = linearLayout.getChildCount();
        for (int i = 0; i < size; i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(textColor);
            }
        }
    }

    private int getDarkerColor(int color) {
        float[] hsv = new float[HSV_TABLE];
        Color.colorToHSV(color, hsv);
        hsv[2] *= FACTOR_DARKER;
        return Color.HSVToColor(hsv);
    }

    private void setColorBackground(LinearLayout container, int color) {
        container.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }
}
