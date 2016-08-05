package com.skala.core.ui.discovermovie;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.DiscoverMovie;
import com.skala.core.api.model.Result;
import com.skala.core.api.net.CallApi;
import com.skala.core.api.repository.ConfigurationRepository;
import com.skala.core.api.repository.VideoRepository;
import com.skala.core.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Skala
 */
@Singleton
public class DiscoverMoviePresenter extends BasePresenter<DiscoverMovieUi> {
    private static final int SIZE_IMAGE = 4; // TODO: delete this

    private final VideoRepository videoApi;
    private final ConfigurationRepository configurationRepository;
    private final List<DiscoverMovieModelView> discoverMovieList = new ArrayList<>();

    @Inject
    public DiscoverMoviePresenter(VideoRepository videoApi, ConfigurationRepository configurationRepository) {
        this.videoApi = videoApi;
        this.configurationRepository = configurationRepository;
    }

    @Override
    protected void onFirstUiAttachment() {
        loadDiscoverMovie();
    }

    public void loadDiscoverMovie() {
        configurationRepository.getConfiguration(new CallApi<ConfigurationApi, String>() {
            @Override
            public void onSuccess(ConfigurationApi configurationApi) {
                loadRealDiscoverMovie(configurationApi);
            }

            @Override
            public void onFailed(String error) {
                execute(ui -> ui.displayError(error));
            }
        });
    }

    public void loadRealDiscoverMovie(ConfigurationApi configurationApi) {
        videoApi.getDiscoverMovie(new CallApi<DiscoverMovie, String>() {
            @Override
            public void onSuccess(DiscoverMovie discoverMovie) {
                String prefixPoster = configurationApi.getImages().getSecureBaseUrl() + configurationApi.getImages().getPosterSizes().get(SIZE_IMAGE);

                discoverMovieList.clear();
                int size = discoverMovie.getResults().size();
                for (int i = 0; i < size; i++) {
                    Result movie = discoverMovie.getResults().get(i);
                    discoverMovieList.add(new DiscoverMovieModelView(movie.getTitle(), movie.getOverview(), prefixPoster + movie.getPosterPath(),
                            movie.getReleaseDate()));
                }

                execute(DiscoverMovieUi::notifyDataChange);
            }

            @Override
            public void onFailed(String error) {
                execute(ui -> ui.displayError(error));
            }
        });
    }

    public List<DiscoverMovieModelView> getDiscoverMovie() {
        return discoverMovieList;
    }
}
