package com.skala.core.api;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.repository.ConfigurationRepository;

import javax.inject.Singleton;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author Skala
 */
@Singleton
public class ConfigurationServiceApi implements ConfigurationRepository {
    private final ConfigurationRestRepository configurationRestRepository;
    private final String apiKey;

    public ConfigurationServiceApi(Retrofit retrofit, String apiKey) {
        configurationRestRepository = retrofit.create(ConfigurationRestRepository.class);
        this.apiKey = apiKey;
    }

    @Override
    public Observable<ConfigurationApi> getConfiguration() {
        return configurationRestRepository.getConfiguration(apiKey);
    }
}
