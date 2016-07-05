package com.skala.core.api;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.net.CallApi;
import com.skala.core.api.repository.ConfigurationRepository;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author Skala
 */
@Singleton
public class ConfigurationServiceApi implements ConfigurationRepository {
    private final ConfigurationRestRepository configurationRestRepository;
    private final String apiKey;
    private ConfigurationApi configurationApi;

    public ConfigurationServiceApi(Retrofit retrofit, String apiKey) {
        configurationRestRepository = retrofit.create(ConfigurationRestRepository.class);
        this.apiKey = apiKey;
    }

    @Override
    public void getConfiguration(CallApi<ConfigurationApi, String> callApi) {
        if (configurationApi != null) {
            callApi.onSuccess(configurationApi);
            return;
        }

        configurationRestRepository.getConfiguration(apiKey).enqueue(new Callback<ConfigurationApi>() {
            @Override
            public void onResponse(Call<ConfigurationApi> call, Response<ConfigurationApi> response) {
                configurationApi = response.body();
                callApi.onSuccess(configurationApi);
            }

            @Override
            public void onFailure(Call<ConfigurationApi> call, Throwable t) {
                callApi.onFailed(t.getMessage());
            }
        });
    }
}
