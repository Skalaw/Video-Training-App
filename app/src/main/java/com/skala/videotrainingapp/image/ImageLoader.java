package com.skala.videotrainingapp.image;

import android.widget.ImageView;

/**
 * @author Skala
 */
public interface ImageLoader {
    void load(String path, ImageView target);
    void load(String path, BitmapTarget target);
}
