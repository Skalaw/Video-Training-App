package com.skala.core.api.ui.DiscoverMovie;

import com.skala.core.api.VideoRepository;
import com.skala.core.api.model.DiscoverMovie;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Skala
 */
public class DiscoverMoviePresenter {
    private final VideoRepository videoApi;
    private DiscoverMovieUi ui;

    @Inject
    public DiscoverMoviePresenter(VideoRepository videoApi) {
        this.videoApi = videoApi;
    }

    public void onAttached(DiscoverMovieUi ui) {
        this.ui = ui;

        loadDiscoverMovie();
    }

    public void onDetach() {
        ui = null;
    }

    private void loadDiscoverMovie() {
        videoApi.getDiscoverMovie().enqueue(new Callback<DiscoverMovie>() {
            @Override
            public void onResponse(Call<DiscoverMovie> call, Response<DiscoverMovie> response) {
                ui.displayMovies(response.body()); // TODO: eliminate nullpointerexception
            }

            @Override
            public void onFailure(Call<DiscoverMovie> call, Throwable t) {
                ui.displayError(t.getMessage()); // TODO: eliminate nullpointerexception
            }
        });
    }
}
