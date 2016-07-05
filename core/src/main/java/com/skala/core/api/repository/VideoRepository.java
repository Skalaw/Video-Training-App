package com.skala.core.api.repository;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.DiscoverMovie;
import com.skala.core.api.net.CallApi;

import retrofit2.Call;

/**
 * @author Skala
 */
public interface VideoRepository {
    Call<AuthenticationToken> getRequestToken();

    Call<AuthenticationToken> getValidateRequestToken(String requestToken,
                                                      String username,
                                                      String password);

    Call<AuthenticationSessionId> getSessionId(String requestToken);

    void getDiscoverMovie(CallApi<DiscoverMovie, String> callApi);
}
