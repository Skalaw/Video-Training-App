package com.skala.videotrainingapp;

import android.app.Application;
import android.content.Context;

import com.skala.core.api.RestVideoApi;
import com.skala.core.api.VideoRepository;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by user on 14/06/16.
 */
public class VideoApp extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new AppModule());
        LeakCanary.install(this);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    public static VideoApp getApp(Context context) {
        return (VideoApp) context.getApplicationContext();
    }

    @Module(library = true)
    public static class AppModule {

        @Singleton
        @Provides
        OkHttpClient provideOkHttpClient() {
            return new OkHttpClient();
        }

        @Singleton
        @Provides
        VideoRepository provideRestVideoApi(OkHttpClient okHttpClient) {
            return new RestVideoApi(okHttpClient, BuildConfig.THE_MOVIE_DB_API_KEY);
        }
    }
}
