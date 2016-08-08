package com.skala.core.api;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.DiscoverMovie;
import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.movievideos.MovieVideos;
import com.skala.core.api.net.CallApi;
import com.skala.core.api.repository.VideoRepository;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    public void getRequestToken(CallApi<AuthenticationToken, String> callResponse) {
        videoRestRepository.getRequestToken(apiKey).enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                callResponse.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                callResponse.onFailed(t.getMessage());
            }
        });
    }

    @Override
    public void getValidateRequestToken(CallApi<AuthenticationToken, String> callResponse, String requestToken, String username, String password) {
        videoRestRepository.getValidateRequestToken(apiKey, requestToken, username, password).enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                callResponse.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                callResponse.onFailed(t.getMessage());
            }
        });
    }

    @Override
    public void getSessionId(CallApi<AuthenticationSessionId, String> callResponse, String requestToken) {
        videoRestRepository.getSessionId(apiKey, requestToken).enqueue(new Callback<AuthenticationSessionId>() {
            @Override
            public void onResponse(Call<AuthenticationSessionId> call, Response<AuthenticationSessionId> response) {
                callResponse.onSuccess(response.body());  // TODO: handle errors (for example when we don't have correct apiKey)
            }

            @Override
            public void onFailure(Call<AuthenticationSessionId> call, Throwable t) {
                callResponse.onFailed(t.getMessage());
            }
        });
    }

    @Override
    public void getDiscoverMovie(CallApi<DiscoverMovie, String> callResponse) {
        videoRestRepository.getDiscoverMovie(apiKey).enqueue(new Callback<DiscoverMovie>() {
            @Override
            public void onResponse(Call<DiscoverMovie> call, Response<DiscoverMovie> response) {
                callResponse.onSuccess(response.body());  // TODO: handle errors (for example when we don't have correct apiKey)
            }

            @Override
            public void onFailure(Call<DiscoverMovie> call, Throwable t) {
                callResponse.onFailed(t.getMessage());
            }
        });
    }

    @Override
    public void getMovieInfo(CallApi<MovieInfo, String> callResponse, int movieId) {
        videoRestRepository.getMovieInfo(movieId, apiKey).enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Call<MovieInfo> call, Response<MovieInfo> response) {
                callResponse.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {
                callResponse.onFailed(t.getMessage());
            }
        });
    }

    @Override
    public void getMovieVideos(CallApi<MovieVideos, String> callResponse, int movieId) {
        videoRestRepository.getMovieVideos(movieId, apiKey).enqueue(new Callback<MovieVideos>() {
            @Override
            public void onResponse(Call<MovieVideos> call, Response<MovieVideos> response) {
                callResponse.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieVideos> call, Throwable t) {
                callResponse.onFailed(t.getMessage());
            }
        });
    }
}
