package com.skala.videotrainingapp;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import dagger.ObjectGraph;

/**
 * @author Skala
 */
public class VideoApp extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new NetModule(getApplicationContext()));
        LeakCanary.install(this);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    public static VideoApp getApp(Context context) {
        return (VideoApp) context.getApplicationContext();
    }
}
