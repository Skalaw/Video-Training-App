package com.skala.core.api;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.discovermovie.DiscoverMoviePages;
import com.skala.core.api.model.movievideos.MovieVideoPages;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Skala
 */
public interface VideoRestRepository {
    String QUERY_API_KEY = "api_key";
    String QUERY_REQUEST_TOKEN = "request_token";
    String QUERY_USER_NAME = "username";
    String QUERY_PASSWORD = "password";
    String QUERY_MOVIE_ID = "movieId";
    String QUERY_PAGE = "page";
    String QUERY_LANGUAGE = "language";

    @GET("authentication/token/new")
    Observable<AuthenticationToken> getRequestToken(@Query(QUERY_API_KEY) String apiKey);

    @GET("authentication/token/new")
    Observable<AuthenticationToken> getValidateRequestToken(@Query(QUERY_API_KEY) String apiKey,
                                                            @Query(QUERY_REQUEST_TOKEN) String requestToken,
                                                            @Query(QUERY_USER_NAME) String username,
                                                            @Query(QUERY_PASSWORD) String password);

    @GET("authentication/session/new")
    Observable<AuthenticationSessionId> getSessionId(@Query(QUERY_API_KEY) String apiKey,
                                                     @Query(QUERY_REQUEST_TOKEN) String requestToken);

    @GET("discover/movie")
    Observable<DiscoverMoviePages> getDiscoverMovie(@Query(QUERY_API_KEY) String apiKey,
                                                    @Query(QUERY_PAGE) int page,
                                                    @Query(QUERY_LANGUAGE) String language);

    @GET("movie/{movieId}")
    Observable<MovieInfo> getMovieInfo(@Path(QUERY_MOVIE_ID) int movieId,
                                       @Query(QUERY_API_KEY) String apiKey,
                                       @Query(QUERY_LANGUAGE) String language);

    @GET("movie/{movieId}/videos")
    Observable<MovieVideoPages> getMovieVideos(@Path(QUERY_MOVIE_ID) int movieId,
                                               @Query(QUERY_API_KEY) String apiKey,
                                               @Query(QUERY_LANGUAGE) String language);
}
