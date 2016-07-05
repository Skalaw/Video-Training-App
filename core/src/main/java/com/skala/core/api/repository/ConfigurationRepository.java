package com.skala.core.api.repository;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.net.CallApi;

/**
 * @author Skala
 */
public interface ConfigurationRepository {
    void getConfiguration(CallApi<ConfigurationApi, String> callApi);
}
