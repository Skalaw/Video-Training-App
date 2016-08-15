package com.skala.core.ui.discovermovie;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.discovermovie.DiscoverMovie;
import com.skala.core.api.model.discovermovie.DiscoverMoviePages;
import com.skala.core.api.repository.ConfigurationRepository;
import com.skala.core.api.repository.VideoRepository;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Skała
 */
@Singleton
public class DiscoverMovieUseCase {
    private static final int SIZE_IMAGE = 4; // TODO: delete this

    private final VideoRepository videoApi;
    private final ConfigurationRepository configurationRepository;

    @Inject
    public DiscoverMovieUseCase(VideoRepository videoApi, ConfigurationRepository configurationRepository) {
        this.videoApi = videoApi;
        this.configurationRepository = configurationRepository;
    }

    public Observable<List<DiscoverMovieModelView>> loadDiscoverMovie() {
        return configurationRepository.getConfiguration()
                .flatMap(configurationApi -> videoApi.getDiscoverMovie()
                        .map(discoverMoviePages -> getDiscoverMovieModelView(configurationApi, discoverMoviePages)));
    }

    private List<DiscoverMovieModelView> getDiscoverMovieModelView(ConfigurationApi configurationApi, DiscoverMoviePages discoverMoviePages) {
        List<DiscoverMovieModelView> discoverMovieModelView = new LinkedList<>();
        String prefixPoster = configurationApi.getImages().getSecureBaseUrl() + configurationApi.getImages().getPosterSizes().get(SIZE_IMAGE);

        List<DiscoverMovie> discoverMovies = discoverMoviePages.getDiscoverMovies();
        int size = discoverMovies.size();
        for (int i = 0; i < size; i++) {
            DiscoverMovie movie = discoverMovies.get(i);
            discoverMovieModelView.add(new DiscoverMovieModelView(movie.getId(), movie.getTitle(), movie.getOverview(),
                    prefixPoster + movie.getPosterPath(), movie.getReleaseDate()));
        }

        return discoverMovieModelView;
    }
}