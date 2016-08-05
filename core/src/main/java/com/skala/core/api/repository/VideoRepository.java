package com.skala.core.api.repository;

import com.skala.core.api.model.AuthenticationSessionId;
import com.skala.core.api.model.AuthenticationToken;
import com.skala.core.api.model.DiscoverMovie;
import com.skala.core.api.net.CallApi;

/**
 * @author Skala
 */
public interface VideoRepository {
    void getRequestToken(CallApi<AuthenticationToken, String> callResponse);

    void getValidateRequestToken(CallApi<AuthenticationToken, String> callResponse,
                                 String requestToken,
                                 String username,
                                 String password);

    void getSessionId(CallApi<AuthenticationSessionId, String> callResponse, String requestToken);

    void getDiscoverMovie(CallApi<DiscoverMovie, String> callResponse);
}
