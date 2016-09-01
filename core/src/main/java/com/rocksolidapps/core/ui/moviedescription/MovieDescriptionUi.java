package com.rocksolidapps.core.ui.moviedescription;

import com.rocksolidapps.core.ui.base.Ui;

/**
 * @author Skała
 */
public interface MovieDescriptionUi extends Ui {
    void displayMovieDescription(MovieDescriptionModelView movieVideos);

    void notifyDataSetChanged();

    void displayError(String error);
}
