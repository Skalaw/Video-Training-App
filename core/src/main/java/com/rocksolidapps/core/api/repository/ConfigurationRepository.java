package com.rocksolidapps.core.api.repository;

import com.rocksolidapps.core.api.model.ConfigurationApi;

import rx.Observable;

/**
 * @author Skala
 */
public interface ConfigurationRepository {
    Observable<ConfigurationApi> getConfiguration();
}
