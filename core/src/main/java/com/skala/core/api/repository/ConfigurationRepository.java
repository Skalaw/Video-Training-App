package com.skala.core.api.repository;

import com.skala.core.api.model.ConfigurationApi;

import rx.Observable;

/**
 * @author Skala
 */
public interface ConfigurationRepository {
    Observable<ConfigurationApi> getConfiguration();
}
