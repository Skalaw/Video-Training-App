package com.skala.videotrainingapp.home;

/**
 * @author Skała
 */
public interface HomeUi {
    void setToolbarTitle(String title);

    void updateToolbarColor(int colorPrimary, int colorPrimaryDark);

    void genresIsReady();

    void openMovieDescription(int movieId);

    void openYoutube(String url);
}
