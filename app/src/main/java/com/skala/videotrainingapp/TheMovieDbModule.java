package com.skala.videotrainingapp;

import com.google.gson.Gson;
import com.skala.core.api.ConfigurationServiceApi;
import com.skala.core.api.VideoServiceApi;
import com.skala.core.api.repository.ConfigurationRepository;
import com.skala.core.api.repository.VideoRepository;

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
public class TheMovieDbModule {
    private static final String THEMOVIEDB_ENDPOINT = "http://api.themoviedb.org/3/";

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(THEMOVIEDB_ENDPOINT)
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    ConfigurationRepository provideConfigurationRepository(Retrofit retrofit) {
        return new ConfigurationServiceApi(retrofit, BuildConfig.THE_MOVIE_DB_API_KEY);
    }

    @Singleton
    @Provides
    VideoRepository provideVideoRepository(Retrofit retrofit) {
        return new VideoServiceApi(retrofit, BuildConfig.THE_MOVIE_DB_API_KEY);
    }
}
