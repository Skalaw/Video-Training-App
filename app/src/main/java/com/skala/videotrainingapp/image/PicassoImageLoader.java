package com.skala.videotrainingapp.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.skala.videotrainingapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import okhttp3.OkHttpClient;

/**
 * @author Skala
 */
public class PicassoImageLoader implements ImageLoader {
    private final Picasso picasso;

    public PicassoImageLoader(Context appContext, OkHttpClient okHttpClient) {
        picasso = new Picasso.Builder(appContext)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
    }

    @Override
    public void load(String path, ImageView target) {
        Drawable placeholder = ContextCompat.getDrawable(target.getContext(), R.drawable.animated_loading); // todo: fix vector drawable bellow api 21
        picasso.load(path).placeholder(placeholder).into(target);
        Drawable drawable = target.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    @Override
    public void load(String path, BitmapTarget target) {
        picasso.load(path).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                target.onBitmapLoaded(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                target.onBitmapFailed(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                target.onPrepareLoad(placeHolderDrawable);
            }
        });
    }
}
