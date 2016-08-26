package com.skala.videotrainingapp.home;

import com.skala.core.api.genre.Genres;

/**
 * @author Skała
 */
public interface HomeUi {
    void setToolbarTitle(String title);

    void updateToolbarColor(int colorPrimary, int colorPrimaryDark);

    void updateGenre(Genres genres);

    void openMovieDescription(int movieId);

    void openYoutube(String url);
}
