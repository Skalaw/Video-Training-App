package com.skala.core.ui.DiscoverMovie;

import com.skala.core.ui.base.Ui;

import java.util.List;

/**
 * @author Skala
 */
public interface DiscoverMovieUi extends Ui {
    void displayMovies(List<DiscoverMovieModelView> modelView);

    void displayError(String message);
}
