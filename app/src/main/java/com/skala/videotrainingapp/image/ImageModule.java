package com.skala.videotrainingapp.image;

import android.content.Context;

import com.skala.videotrainingapp.AndroidModule;
import com.skala.videotrainingapp.NetModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * @author Skala
 */
@Module(library = true, includes = {AndroidModule.class, NetModule.class})
public class ImageModule {

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Context appContext, OkHttpClient okHttpClient) {
        return new PicassoImageLoader(appContext, okHttpClient);
    }
}
