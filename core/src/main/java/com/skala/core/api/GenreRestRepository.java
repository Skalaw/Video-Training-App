package com.skala.core.api;

import com.skala.core.api.genre.Genres;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Skała
 */
public interface GenreRestRepository {
    String QUERY_API_KEY = "api_key";
    String QUERY_LANGUAGE = "language";

    @GET("genre/movie/list")
    Observable<Genres> getMovieGenres(@Query(QUERY_API_KEY) String apiKey,
                                      @Query(QUERY_LANGUAGE) String language);
}
