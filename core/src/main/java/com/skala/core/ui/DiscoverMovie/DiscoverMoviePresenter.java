package com.skala.core.ui.DiscoverMovie;

import com.skala.core.api.VideoRepository;
import com.skala.core.api.model.DiscoverMovie;
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

    private List<UiCommand> uiCommands = new ArrayList<>();

    @Inject
    public DiscoverMoviePresenter(VideoRepository videoApi) {
        this.videoApi = videoApi;
    }

    public void onAttached(DiscoverMovieUi ui) {
        this.ui = ui;

        loadDiscoverMovie(); // TODO: load only first time

        executePendingUiCommands();
    }

    private void loadDiscoverMovie() {
        videoApi.getDiscoverMovie().enqueue(new Callback<DiscoverMovie>() {
            @Override
            public void onResponse(Call<DiscoverMovie> call, Response<DiscoverMovie> response) {
                DisplayMovies displayMovies = new DisplayMovies(response.body());
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
