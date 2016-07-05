package com.skala.videotrainingapp;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.skala.core.api.VideoServiceApi;
import com.skala.core.api.repository.VideoRepository;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Skala
 */
public class VideoApp extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new NetModule());
        LeakCanary.install(this);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    public static VideoApp getApp(Context context) {
        return (VideoApp) context.getApplicationContext();
    }
}
