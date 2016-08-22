package com.skala.core.api.repository;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.discovermovie.DiscoverMoviePages;
import com.skala.core.api.model.movievideos.MovieVideoPages;

import rx.Observable;

/**
 * @author Skala
 */
public interface VideoRepository {
    Observable<AuthenticationToken> getRequestToken();

    Observable<AuthenticationToken> getValidateRequestToken(String requestToken,
                                                            String username,
                                                            String password);

    Observable<AuthenticationSessionId> getSessionId(String requestToken);

    Observable<DiscoverMoviePages> getDiscoverMovie(int page);

    Observable<MovieInfo> getMovieInfo(int movieId);

    Observable<MovieVideoPages> getMovieVideos(int movieId);
}
