package com.skala.core.ui.discovermovie;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.discovermovie.DiscoverMovie;
import com.skala.core.api.model.discovermovie.DiscoverMoviePages;
import com.skala.core.api.net.CallApi;
import com.skala.core.api.repository.ConfigurationRepository;
import com.skala.core.api.repository.VideoRepository;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Skała
 */
@Singleton
public class DiscoverMovieListUseCase {
    private static final int SIZE_IMAGE = 4; // TODO: delete this

    private final VideoRepository videoApi;
    private final ConfigurationRepository configurationRepository;

    @Inject
    public DiscoverMovieListUseCase(VideoRepository videoApi, ConfigurationRepository configurationRepository) {
        this.videoApi = videoApi;
        this.configurationRepository = configurationRepository;
    }

    public void loadDiscoverMovie(CallApi<List<DiscoverMovieModelView>, String> callApiResponse) {
        configurationRepository.getConfiguration(new CallApi<ConfigurationApi, String>() {
            @Override
            public void onSuccess(ConfigurationApi configurationApi) {
                loadDiscoverMovie(callApiResponse, configurationApi);
            }

            @Override
            public void onFailed(String error) {
                callApiResponse.onFailed(error);
            }
        });
    }

    public void loadDiscoverMovie(CallApi<List<DiscoverMovieModelView>, String> callApiResponse, ConfigurationApi configurationApi) {
        videoApi.getDiscoverMovie(new CallApi<DiscoverMoviePages, String>() {
            @Override
            public void onSuccess(DiscoverMoviePages discoverMovies) {
                String prefixPoster = configurationApi.getImages().getSecureBaseUrl() + configurationApi.getImages().getPosterSizes().get(SIZE_IMAGE);
                List<DiscoverMovieModelView> discoverMovieModelView = new LinkedList<>();

                int size = discoverMovies.getResults().size();
                for (int i = 0; i < size; i++) {
                    DiscoverMovie movie = discoverMovies.getResults().get(i);
                    discoverMovieModelView.add(new DiscoverMovieModelView(movie.getId(), movie.getTitle(), movie.getOverview(),
                            prefixPoster + movie.getPosterPath(), movie.getReleaseDate()));
                }

                callApiResponse.onSuccess(discoverMovieModelView);
            }

            @Override
            public void onFailed(String error) {
                callApiResponse.onFailed(error);
            }
        });
    }
}
