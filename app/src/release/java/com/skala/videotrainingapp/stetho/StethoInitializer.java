package com.skala.videotrainingapp.stetho;

import android.content.Context;

/**
 * @author Skała
 */
public final class StethoInitializer {
    private StethoInitializer() {
        // ignore
    }

    public static void init(Context context) {
        // not initialize for release
    }

    public static okhttp3.Interceptor getInterceptor() {
        //not initialize for beta/live
        return chain -> chain.proceed(chain.request());
    }
}