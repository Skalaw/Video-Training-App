package com.skala.core.api;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.ConfigurationApi;

import retrofit2.Call;

/**
 * @author Skala
 */
public interface VideoRepository {
    Call<ConfigurationApi> getConfiguration(String apiKey);

    Call<AuthenticationToken> getRequestToken(String apiKey);

    Call<AuthenticationToken> getValidateRequestToken(String apiKey,
                                                      String requestToken,
                                                      String username,
                                                      String password);

    Call<AuthenticationSessionId> getSessionId(String apiKey,
                                               String requestToken);
}
