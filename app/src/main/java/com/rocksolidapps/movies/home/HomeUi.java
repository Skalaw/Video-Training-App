package com.rocksolidapps.movies.home;

/**
 * @author Skała
 */
public interface HomeUi {
    void setToolbarTitle(String title);

    void updateToolbarColor(int colorPrimary, int colorPrimaryDark);

    void genresIsReady();

    void setButtonGenre(String genre);

    void setButtonSort(String sort);

    void openMovieDescription(int movieId);

    void openYoutube(String url);
}
