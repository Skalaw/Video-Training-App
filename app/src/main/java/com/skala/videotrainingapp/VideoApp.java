package com.skala.videotrainingapp;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.skala.core.uithread.UiThread;
import com.squareup.leakcanary.LeakCanary;

import dagger.ObjectGraph;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author Skala
 */
public class VideoApp extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new AndroidModule(getApplicationContext()), new AppModule());
        LeakCanary.install(this);
        UiThread.init(AndroidSchedulers::mainThread);
        Stetho.initializeWithDefaults(this);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    public static VideoApp getApp(Context context) {
        return (VideoApp) context.getApplicationContext();
    }
}
