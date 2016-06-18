package com.skala.core.ui.DiscoverMovie;

import com.skala.core.api.VideoRepository;
import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.DiscoverMovie;
import com.skala.core.api.model.Result;
import com.skala.core.command.DisplayError;
import com.skala.core.command.DisplayMovies;
import com.skala.core.command.UiCommand;

import java.util.ArrayList;
import java.util.List;

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

    private ConfigurationApi configurationApi;

    private List<UiCommand> uiCommands = new ArrayList<>();

    @Inject
    public DiscoverMoviePresenter(VideoRepository videoApi) {
        this.videoApi = videoApi;
    }

    public void onAttached(DiscoverMovieUi ui) {
        this.ui = ui;

        loadConfig(); // TODO: load only first time & used only one when used app

        executePendingUiCommands();
    }

    private void loadConfig() {
        videoApi.getConfiguration().enqueue(new Callback<ConfigurationApi>() {
            @Override
            public void onResponse(Call<ConfigurationApi> call, Response<ConfigurationApi> response) {
                configurationApi = response.body();
                loadDiscoverMovie(); // TODO: move this onAttached when config download in other place
            }

            @Override
            public void onFailure(Call<ConfigurationApi> call, Throwable t) {

            }
        });
    }

    private void loadDiscoverMovie() {
        videoApi.getDiscoverMovie().enqueue(new Callback<DiscoverMovie>() {
            @Override
            public void onResponse(Call<DiscoverMovie> call, Response<DiscoverMovie> response) {
                DiscoverMovie discoverMovie = response.body();

                List<DiscoverMovieModelView> modelViews = new ArrayList<>();
                int size = discoverMovie.getResults().size();
                for (int i = 0; i < size; i++) {
                    Result movie = discoverMovie.getResults().get(i);
                    modelViews.add(new DiscoverMovieModelView(movie.getTitle(), movie.getOverview(), configurationApi.getImages().getSecureBaseUrl() + configurationApi.getImages().getPosterSizes().get(3) + movie.getPosterPath())); // TODO: delete .get(3)
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

    private void executePendingUiCommands() {
        int size = uiCommands.size();
        for (int i = 0; i < size; i++) {
            uiCommands.get(i).execute(ui);
        }

        uiCommands.clear();
    }

    public void onDetach() {
        ui = null;
    }

    private void execute(UiCommand uiCommand) {
        if (ui == null) {
            uiCommands.add(uiCommand);
        } else {
            uiCommand.execute(ui);
        }
    }
}
