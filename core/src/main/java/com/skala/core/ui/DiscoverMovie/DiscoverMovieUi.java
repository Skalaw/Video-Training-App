package com.skala.core.ui.DiscoverMovie;

import java.util.List;

/**
 * @author Skala
 */
public interface DiscoverMovieUi {
    void displayMovies(List<DiscoverMovieModelView> modelView);

    void displayError(String message);
}
