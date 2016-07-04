package com.skala.core.command;

import com.skala.core.ui.discovermovie.DiscoverMovieUi;

/**
 * @author Skala
 */
public class DisplayMovies implements UiCommand<DiscoverMovieUi> {

    @Override
    public void execute(DiscoverMovieUi ui) {
        ui.notifyDataChange();
    }
}
