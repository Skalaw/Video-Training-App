package com.rocksolidapps.movies;

import com.google.gson.Gson;
import com.rocksolidapps.core.api.ConfigurationServiceApi;
import com.rocksolidapps.core.api.GenreServiceApi;
import com.rocksolidapps.core.api.VideoServiceApi;
import com.rocksolidapps.core.api.repository.ConfigurationRepository;
import com.rocksolidapps.core.api.repository.GenreRepository;
import com.rocksolidapps.core.api.repository.VideoRepository;

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

    @Singleton
    @Provides
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

    @Singleton
    @Provides
    GenreRepository provideGenreRepository(Retrofit retrofit) {
        return new GenreServiceApi(retrofit, BuildConfig.THE_MOVIE_DB_API_KEY);
    }
}
