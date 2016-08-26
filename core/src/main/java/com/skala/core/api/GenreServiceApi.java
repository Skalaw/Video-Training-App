package com.skala.core.api;

import com.skala.core.api.genre.Genres;
import com.skala.core.api.repository.GenreRepository;

import javax.inject.Singleton;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author Skała
 */
@Singleton
public class GenreServiceApi implements GenreRepository {
    private final static String LANGUAGE_DEFAULT = "en"; // todo move this

    private final GenreRestRepository genreRestRepository;
    private final String apiKey;

    public GenreServiceApi(Retrofit retrofit, String apiKey) {
        this.genreRestRepository = retrofit.create(GenreRestRepository.class);
        this.apiKey = apiKey;
    }

    @Override
    public Observable<Genres> getMovieGenres() {
        return genreRestRepository.getMovieGenres(apiKey, LANGUAGE_DEFAULT);
    }
}
