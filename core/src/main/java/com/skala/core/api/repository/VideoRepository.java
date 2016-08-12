package com.skala.core.api.repository;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.discovermovie.DiscoverMoviePages;
import com.skala.core.api.model.movievideos.MovieVideoPages;
import com.skala.core.api.net.CallApi;

import rx.Observable;

/**
 * @author Skala
 */
public interface VideoRepository {
    void getRequestToken(CallApi<AuthenticationToken, String> callResponse);

    void getValidateRequestToken(CallApi<AuthenticationToken, String> callResponse,
                                 String requestToken,
                                 String username,
                                 String password);

    void getSessionId(CallApi<AuthenticationSessionId, String> callResponse, String requestToken);

    Observable<DiscoverMoviePages> getDiscoverMovie();

    Observable<MovieInfo> getMovieInfo(int movieId);

    Observable<MovieVideoPages> getMovieVideos(int movieId);
}
