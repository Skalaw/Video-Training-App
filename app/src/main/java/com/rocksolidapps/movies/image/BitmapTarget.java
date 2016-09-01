package com.rocksolidapps.movies.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * @author Skała
 */
public interface BitmapTarget {
    void onBitmapLoaded(Bitmap bitmap);

    void onBitmapFailed(Drawable errorDrawable);

    void onPrepareLoad(Drawable placeHolderDrawable);
}
