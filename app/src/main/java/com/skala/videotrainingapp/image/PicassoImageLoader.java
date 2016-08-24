package com.skala.videotrainingapp.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
        picasso.load(path).placeholder(R.drawable.place_holder).into(target); // todo maybe change in future place_holder - AnimationVectorDrawable ?
    }

    @Override
    public void load(String path, BitmapTarget target) {
        picasso.load(path).placeholder(R.drawable.place_holder).into(new Target() {
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
