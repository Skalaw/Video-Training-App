package com.skala.core.ui.moviedescription;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.movievideos.MovieVideos;
import com.skala.core.ui.base.Ui;

/**
 * @author Skała
 */
public interface MovieDescriptionUi extends Ui {
    void displayMovieInfo(MovieInfo movieInfo, ConfigurationApi configurationApi); // todo delete configurationApi

    void displayMovieVideos(MovieVideos movieVideos);

    void displayError(String error);
}
