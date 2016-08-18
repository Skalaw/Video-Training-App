package com.skala.videotrainingapp;

import android.content.Context;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * @author Skala
 */
@Module(library = true, includes = AndroidModule.class)
public class NetModule {
    private static final int SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final int CACHE_MAX_AGE_HOURS = 12;

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    Interceptor provideInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(CACHE_MAX_AGE_HOURS, TimeUnit.HOURS)
                    .build();
            return response.newBuilder()
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build();
        };
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(Context context, Interceptor interceptor) {
        Cache cache = new Cache(context.getCacheDir(), SIZE_OF_CACHE);
        return new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(interceptor)
                .build();
    }
}