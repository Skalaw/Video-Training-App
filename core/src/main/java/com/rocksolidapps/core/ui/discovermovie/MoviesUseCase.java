package com.rocksolidapps.core.ui.discovermovie;

import com.rocksolidapps.core.api.model.ConfigurationApi;
import com.rocksolidapps.core.api.model.discovermovie.DiscoverMovie;
import com.rocksolidapps.core.api.model.discovermovie.DiscoverMoviePages;
import com.rocksolidapps.core.api.repository.ConfigurationRepository;
import com.rocksolidapps.core.api.repository.VideoRepository;
import com.rocksolidapps.core.ui.ScreenSize;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Skała
 */
@Singleton
public class MoviesUseCase {
    private final VideoRepository videoApi;
    private final ConfigurationRepository configurationRepository;
    private final ScreenSize screenSize;

    @Inject
    public MoviesUseCase(VideoRepository videoApi, ConfigurationRepository configurationRepository, ScreenSize screenSize) {
        this.videoApi = videoApi;
        this.configurationRepository = configurationRepository;
        this.screenSize = screenSize;
    }

    public Observable<List<DiscoverMovieModelView>> loadDiscoverMovie(int page, List<Integer> genreIds, String sort) {
        return configurationRepository.getConfiguration()
                .flatMap(configurationApi -> videoApi.getDiscoverMovie(page, genreIds, sort)
                        .map(discoverMoviePages -> getDiscoverMovieModelView(configurationApi, discoverMoviePages)));
    }

    private List<DiscoverMovieModelView> getDiscoverMovieModelView(ConfigurationApi configurationApi, DiscoverMoviePages discoverMoviePages) {
        List<DiscoverMovieModelView> discoverMovieModelView = new LinkedList<>();
        String prefixPoster = configurationApi.getImages().getSecureBaseUrl() + screenSize.getPosterSize(configurationApi.getImages().getPosterSizes());

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