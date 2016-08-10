package com.skala.core.ui.moviedescription;

import com.skala.core.ui.base.Ui;

/**
 * @author Skała
 */
public interface MovieDescriptionUi extends Ui {
    void displayMovieDescription(MovieDescriptionModelView movieVideos);

    void displayError(String error);
}
