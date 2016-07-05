package com.skala.core.api;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.DiscoverMovie;
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
    public Call<AuthenticationToken> getRequestToken() {
        return videoRestRepository.getRequestToken(apiKey);
    }

    @Override
    public Call<AuthenticationToken> getValidateRequestToken(String requestToken, String username, String password) {
        return videoRestRepository.getValidateRequestToken(apiKey, requestToken, username, password);
    }

    @Override
    public Call<AuthenticationSessionId> getSessionId(String requestToken) {
        return videoRestRepository.getSessionId(apiKey, requestToken);
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
}
