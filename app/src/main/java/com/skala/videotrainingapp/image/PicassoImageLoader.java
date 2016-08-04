package com.skala.videotrainingapp.image;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * @author Skala
 */
public class PicassoImageLoader implements ImageLoader {
    private final Picasso picasso;

    public PicassoImageLoader(Context appContext) {
        picasso = Picasso.with(appContext);
    }

    @Override
    public void load(String path, ImageView target) {
        picasso.load(path).into(target);
    }
}
