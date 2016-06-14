package com.skala.core.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.ConfigurationApi;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Skala
 *         API: http://docs.themoviedb.apiary.io/
 */
public class RestVideoApi implements VideoRepository {
    private static final String ENDPOINT = "http://api.themoviedb.org/3/";
    private VideoApi videoApi;
    private String apiKey;

    @Inject
    public RestVideoApi(OkHttpClient client, String apiKey) {
        this.apiKey = apiKey;
        final Gson gson = new GsonBuilder()
                .create();

        videoApi = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(VideoApi.class);
    }

    @Override
    public Call<ConfigurationApi> getConfiguration(String apiKey) {
        return videoApi.getConfiguration(apiKey);
    }

    @Override
    public Call<AuthenticationToken> getRequestToken(String apiKey) {
        return videoApi.getRequestToken(apiKey);
    }

    @Override
    public Call<AuthenticationToken> getValidateRequestToken(String apiKey, String requestToken, String username, String password) {
        return videoApi.getValidateRequestToken(apiKey, requestToken, username, password);
    }

    @Override
    public Call<AuthenticationSessionId> getSessionId(String apiKey, String requestToken) {
        return videoApi.getSessionId(apiKey, requestToken);
    }
}
