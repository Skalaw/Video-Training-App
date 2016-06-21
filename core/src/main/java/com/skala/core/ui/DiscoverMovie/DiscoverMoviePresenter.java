package com.skala.core.ui.DiscoverMovie;

import com.skala.core.api.VideoRepository;
import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.DiscoverMovie;
import com.skala.core.api.model.Result;
import com.skala.core.command.DisplayError;
import com.skala.core.command.DisplayMovies;
import com.skala.core.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Skala
 */
@Singleton
public class DiscoverMoviePresenter extends BasePresenter<DiscoverMovieUi> {
    private static final int SIZE_IMAGE = 4; // TODO: delete this

    private final VideoRepository videoApi;

    private ConfigurationApi configurationApi;

    @Inject
    public DiscoverMoviePresenter(VideoRepository videoApi) {
        this.videoApi = videoApi;
    }

    @Override
    protected void onFirstUiAttachment() {
        loadConfig(); // TODO: cache config + discoverMovie list & add command what should reloaded on view when for example rotate screen
    }

    private void loadConfig() {
        videoApi.getConfiguration().enqueue(new Callback<ConfigurationApi>() {
            @Override
            public void onResponse(Call<ConfigurationApi> call, Response<ConfigurationApi> response) {
                configurationApi = response.body(); // TODO: handle errors (for example when we don't have correct apiKey)
                loadDiscoverMovie(); // TODO: move this onAttached when config download in other place
            }

            @Override
            public void onFailure(Call<ConfigurationApi> call, Throwable t) {
                DisplayError displayError = new DisplayError(t.getMessage());
                execute(displayError);
            }
        });
    }

    private void loadDiscoverMovie() {
        videoApi.getDiscoverMovie().enqueue(new Callback<DiscoverMovie>() {
            @Override
            public void onResponse(Call<DiscoverMovie> call, Response<DiscoverMovie> response) {
                DiscoverMovie discoverMovie = response.body();

                String prefixPoster = configurationApi.getImages().getSecureBaseUrl() + configurationApi.getImages().getPosterSizes().get(SIZE_IMAGE);

                List<DiscoverMovieModelView> modelViews = new ArrayList<>();
                int size = discoverMovie.getResults().size();
                for (int i = 0; i < size; i++) {
                    Result movie = discoverMovie.getResults().get(i);
                    modelViews.add(new DiscoverMovieModelView(movie.getTitle(), movie.getOverview(), prefixPoster + movie.getPosterPath(),
                            movie.getReleaseDate()));
                }

                DisplayMovies displayMovies = new DisplayMovies(modelViews);
                execute(displayMovies);
            }

            @Override
            public void onFailure(Call<DiscoverMovie> call, Throwable t) {
                DisplayError displayError = new DisplayError(t.getMessage());
                execute(displayError);
            }
        });
    }
}
