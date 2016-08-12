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
import rx.functions.Func1;

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

    public Observable<List<DiscoverMovieModelView>> loadDiscoverMovie() {
        return configurationRepository.getConfiguration().flatMap(new Func1<ConfigurationApi, Observable<List<DiscoverMovieModelView>>>() {
            @Override
            public Observable<List<DiscoverMovieModelView>> call(ConfigurationApi configurationApi) {
                return videoApi.getDiscoverMovie().map(new Func1<DiscoverMoviePages, List<DiscoverMovieModelView>>() {
                    @Override
                    public List<DiscoverMovieModelView> call(DiscoverMoviePages discoverMoviePages) {
                        String prefixPoster = configurationApi.getImages().getSecureBaseUrl() + configurationApi.getImages().getPosterSizes().get(SIZE_IMAGE);
                        List<DiscoverMovieModelView> discoverMovieModelView = new LinkedList<>();

                        int size = discoverMoviePages.getResults().size();
                        for (int i = 0; i < size; i++) {
                            DiscoverMovie movie = discoverMoviePages.getResults().get(i);
                            discoverMovieModelView.add(new DiscoverMovieModelView(movie.getId(), movie.getTitle(), movie.getOverview(),
                                    prefixPoster + movie.getPosterPath(), movie.getReleaseDate()));
                        }
                        return discoverMovieModelView;
                    }
                });
            }
        });
    }
}