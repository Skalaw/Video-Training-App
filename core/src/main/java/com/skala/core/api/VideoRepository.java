package com.skala.core.api;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.DiscoverMovie;

import retrofit2.Call;

/**
 * @author Skala
 */
public interface VideoRepository {
    Call<ConfigurationApi> getConfiguration();

    Call<AuthenticationToken> getRequestToken();

    Call<AuthenticationToken> getValidateRequestToken(String requestToken,
                                                      String username,
                                                      String password);

    Call<AuthenticationSessionId> getSessionId(String requestToken);

    Call<DiscoverMovie> getDiscoverMovie();
}
