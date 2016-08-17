package com.skala.videotrainingapp;

import com.google.gson.Gson;
import com.skala.core.api.YoutubeServiceApi;
import com.skala.core.api.repository.YoutubeRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Skała
 */
@Module(library = true, includes = NetModule.class)
public class YoutubeModule {
    private static final String YOUTUBE_ENDPOINT = "https://www.youtube.com/";

    @Singleton
    @Provides
    YoutubeRepository provideYoutubeRepository(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofitYoutube = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(YOUTUBE_ENDPOINT)
                .client(okHttpClient)
                .build();

        return new YoutubeServiceApi(retrofitYoutube);
    }
}
