package com.skala.core.ui.DiscoverMovie;

import com.skala.core.api.model.DiscoverMovie;

/**
 * @author Skala
 */
public interface DiscoverMovieUi {
    void displayMovies(DiscoverMovie discoverMovie);

    void displayError(String message);
}
