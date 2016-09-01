package com.rocksolidapps.movies.image;

import android.content.Context;

import com.rocksolidapps.movies.AndroidModule;
import com.rocksolidapps.movies.NetModule;

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
