package com.skala.core.api;

import com.skala.core.api.model.ConfigurationApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Skala
 */
public interface ConfigurationRestRepository {
    String QUERY_API_KEY = "api_key";

    @GET("configuration")
    Call<ConfigurationApi> getConfiguration(@Query(QUERY_API_KEY) String apiKey);
}