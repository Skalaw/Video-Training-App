package com.skala.videotrainingapp;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Skala
 */
@Module(library = true)
public class AndroidModule {
    private final Context appContext;

    public AndroidModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }
}
