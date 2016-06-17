package com.skala.core.command;

import com.skala.core.api.model.DiscoverMovie;
import com.skala.core.ui.DiscoverMovie.DiscoverMovieUi;

/**
 * @author Skala
 */
public class DisplayMovies implements UiCommand<DiscoverMovieUi> {
    private DiscoverMovie discoverMovie;

    public DisplayMovies(DiscoverMovie messages) {
        this.discoverMovie = messages;
    }

    @Override
    public void execute(DiscoverMovieUi ui) {
        ui.displayMovies(discoverMovie);
    }
}
