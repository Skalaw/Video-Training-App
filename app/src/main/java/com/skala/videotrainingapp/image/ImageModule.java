package com.skala.videotrainingapp.image;

import android.content.Context;

import com.skala.videotrainingapp.AndroidModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Skala
 */
@Module(library = true, includes = AndroidModule.class)
public class ImageModule {

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Context appContext) {
        return new PicassoImageLoader(appContext);
    }
}
