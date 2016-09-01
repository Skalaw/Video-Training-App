package com.rocksolidapps.movies.stetho;

import android.content.Context;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

/**
 * @author Skała
 */
public final class StethoInitializer {
    private StethoInitializer() {
        // ignore
    }

    public static void init(Context context) {
        Stetho.initializeWithDefaults(context);
    }

    public static okhttp3.Interceptor getInterceptor() {
        return new StethoInterceptor();
    }
}