package com.skala.core.api;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.DiscoverMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Skala
 */
public interface VideoRestRepository {
    String QUERY_API_KEY = "api_key";
    String QUERY_REQUEST_TOKEN = "request_token";
    String QUERY_USER_NAME = "username";
    String QUERY_PASSWORD = "password";

    @GET("authentication/token/new")
    Call<AuthenticationToken> getRequestToken(@Query(QUERY_API_KEY) String apiKey);

    @GET("authentication/token/new")
    Call<AuthenticationToken> getValidateRequestToken(@Query(QUERY_API_KEY) String apiKey,
                                                      @Query(QUERY_REQUEST_TOKEN) String requestToken,
                                                      @Query(QUERY_USER_NAME) String username,
                                                      @Query(QUERY_PASSWORD) String password);

    @GET("authentication/session/new")
    Call<AuthenticationSessionId> getSessionId(@Query(QUERY_API_KEY) String apiKey,
                                               @Query(QUERY_REQUEST_TOKEN) String requestToken);

    @GET("discover/movie")
    Call<DiscoverMovie> getDiscoverMovie(@Query(QUERY_API_KEY) String apiKey);
}
