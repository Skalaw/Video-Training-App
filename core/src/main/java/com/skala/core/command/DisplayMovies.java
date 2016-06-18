package com.skala.core.command;

import com.skala.core.ui.DiscoverMovie.DiscoverMovieModelView;
import com.skala.core.ui.DiscoverMovie.DiscoverMovieUi;

import java.util.List;

/**
 * @author Skala
 */
public class DisplayMovies implements UiCommand<DiscoverMovieUi> {
    private List<DiscoverMovieModelView> modelView;

    public DisplayMovies(List<DiscoverMovieModelView> modelView) {
        this.modelView = modelView;
    }

    @Override
    public void execute(DiscoverMovieUi ui) {
        ui.displayMovies(modelView);
    }
}
