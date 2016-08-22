package com.skala.core.api;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.discovermovie.DiscoverMoviePages;
import com.skala.core.api.model.movievideos.MovieVideoPages;
import com.skala.core.api.repository.VideoRepository;

import javax.inject.Singleton;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author Skala
 *         API: http://docs.themoviedb.apiary.io/
 */
@Singleton
public class VideoServiceApi implements VideoRepository {
    private final VideoRestRepository videoRestRepository;
    private final String apiKey;

    public VideoServiceApi(Retrofit retrofit, String apiKey) {
        videoRestRepository = retrofit.create(VideoRestRepository.class);
        this.apiKey = apiKey;
    }

    @Override
    public Observable<AuthenticationToken> getRequestToken() {
        return videoRestRepository.getRequestToken(apiKey);
    }

    @Override
    public Observable<AuthenticationToken> getValidateRequestToken(String requestToken, String username, String password) {
        return videoRestRepository.getValidateRequestToken(apiKey, requestToken, username, password);
    }

    @Override
    public Observable<AuthenticationSessionId> getSessionId(String requestToken) {
        return videoRestRepository.getSessionId(apiKey, requestToken);
    }

    @Override
    public Observable<DiscoverMoviePages> getDiscoverMovie(int page) {
        return videoRestRepository.getDiscoverMovie(apiKey, page);
    }

    @Override
    public Observable<MovieInfo> getMovieInfo(int movieId) {
        return videoRestRepository.getMovieInfo(movieId, apiKey);
    }

    @Override
    public Observable<MovieVideoPages> getMovieVideos(int movieId) {
        return videoRestRepository.getMovieVideos(movieId, apiKey);
    }
}
