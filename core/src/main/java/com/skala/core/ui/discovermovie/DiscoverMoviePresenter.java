package com.skala.core.ui.discovermovie;

import com.skala.core.api.genre.Genre;
import com.skala.core.api.genre.Genres;
import com.skala.core.ui.base.BasePresenter;
import com.skala.core.uithread.UiThread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.schedulers.Schedulers;

/**
 * @author Skala
 */
@Singleton
public class DiscoverMoviePresenter extends BasePresenter<DiscoverMovieUi> {
    private final static int DISCOVER_FIRST_PAGE = 1;
    private final static int GENRE_INVALID_ID = -1;

    private final MoviesUseCase moviesUseCase;
    private final GenreMoviesUseCase genreMoviesUseCase;
    private final List<DiscoverMovieModelView> discoverMovieList = new ArrayList<>();
    private Genres genres;
    private int genreId = GENRE_INVALID_ID;
    private int page = DISCOVER_FIRST_PAGE;
    private boolean isMovieLoading = false;
    private boolean isLastPage = false; // todo check when isLastPage

    @Inject
    public DiscoverMoviePresenter(MoviesUseCase moviesUseCase, GenreMoviesUseCase genreMoviesUseCase) {
        this.moviesUseCase = moviesUseCase;
        this.genreMoviesUseCase = genreMoviesUseCase;
    }

    @Override
    protected void onFirstUiAttachment() {
        loadDiscoverMovie(DISCOVER_FIRST_PAGE);
        loadGenreMovie();
    }

    public void loadNextDiscoverMovie() {
        if (isLoadMoviesAvailable()) {
            page++;
            loadDiscoverMovie(page);
        }
    }

    private void loadGenreMovie() {
        genreMoviesUseCase.loadGenreMovies()
                .observeOn(UiThread.uiScheduler())
                .subscribeOn(Schedulers.io())
                .subscribe(genres -> {
                    this.genres = genres;
                    execute(DiscoverMovieUi::genresIsReady);
                });
    }

    public List<Genre> getGenreList() {
        return genres.getGenres();
    }

    public void clearMoviesGenre() {
        setAndLoadMoviesGenre(GENRE_INVALID_ID);
    }

    public void setAndLoadMoviesGenre(int genreId) {
        this.genreId = genreId;
        refreshDiscoverMovie();
    }

    private boolean isLoadMoviesAvailable() {
        return !isMovieLoading && !isLastPage;
    }

    public void refreshDiscoverMovie() {
        clearMovieList();
        loadDiscoverMovie(DISCOVER_FIRST_PAGE);
    }

    private void loadDiscoverMovie(int page) {
        isMovieLoading = true;

        List<Integer> genreIds = getGenresIds(genreId);
        moviesUseCase.loadDiscoverMovie(page, genreIds)
                .observeOn(UiThread.uiScheduler())
                .subscribeOn(Schedulers.io())
                .subscribe(this::showDiscoverMovie, throwable -> {
                    execute(ui -> ui.displayError(throwable.toString()));
                    isMovieLoading = false;
                });
    }

    private List<Integer> getGenresIds(int genreId) {
        List<Integer> genreIds = null;
        if (genreId != GENRE_INVALID_ID) {
            genreIds = new LinkedList<>();
            genreIds.add(genreId);
        }
        return genreIds;
    }

    private void clearMovieList() {
        discoverMovieList.clear();
        page = DISCOVER_FIRST_PAGE;
    }

    private void showDiscoverMovie(List<DiscoverMovieModelView> discoverMovieModelView) {
        discoverMovieList.addAll(discoverMovieModelView);
        execute(DiscoverMovieUi::notifyDataChange);
        isMovieLoading = false;
    }

    public List<DiscoverMovieModelView> getDiscoverMovie() {
        return discoverMovieList;
    }
}