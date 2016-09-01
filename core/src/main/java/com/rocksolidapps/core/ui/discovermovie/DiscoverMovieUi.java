package com.rocksolidapps.core.ui.discovermovie;

import com.rocksolidapps.core.ui.base.Ui;

/**
 * @author Skala
 */
public interface DiscoverMovieUi extends Ui {
    void notifyDataChange();

    void displayError(String message);

    void genresIsReady();
}
