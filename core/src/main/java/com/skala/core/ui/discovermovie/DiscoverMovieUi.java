package com.skala.core.ui.discovermovie;

import com.skala.core.ui.base.Ui;

/**
 * @author Skala
 */
public interface DiscoverMovieUi extends Ui {
    void notifyDataChange();

    void displayError(String message);

    void genresIsReady();
}
