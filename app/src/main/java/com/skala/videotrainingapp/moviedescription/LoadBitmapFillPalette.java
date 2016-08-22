package com.skala.videotrainingapp.moviedescription;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.skala.videotrainingapp.home.HomeUi;
import com.skala.videotrainingapp.image.BitmapTarget;

import java.lang.ref.WeakReference;

/**
 * @author Skała
 */
public class LoadBitmapFillPalette implements BitmapTarget {
    private final static float FACTOR_DARKER = 0.8f;
    private final static int HSV_TABLE = 3;

    private final WeakReference<ImageView> imageTarget;
    private final WeakReference<HomeUi> homeUi;

    public LoadBitmapFillPalette(ImageView imageTarget, HomeUi homeUi) {
        this.imageTarget = new WeakReference<>(imageTarget);
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
        if (homeUi == null) {
            return;
        }

        Palette.Swatch swatch = palette.getVibrantSwatch();
        if (swatch == null) {
            swatch = palette.getMutedSwatch();
        }
        if (swatch != null) {
            int primaryColor = swatch.getRgb();
            int primaryDarkColor = getDarkerColor(primaryColor);
            //int textColor = swatch.getTitleTextColor(); // todo

            homeUi.updateToolbarColor(primaryColor, primaryDarkColor);
        }
    }

    private int getDarkerColor(int color) {
        float[] hsv = new float[HSV_TABLE];
        Color.colorToHSV(color, hsv);
        hsv[2] *= FACTOR_DARKER;
        return Color.HSVToColor(hsv);
    }
}
