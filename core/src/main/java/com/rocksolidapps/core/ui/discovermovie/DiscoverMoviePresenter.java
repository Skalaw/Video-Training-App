package com.rocksolidapps.core.ui.discovermovie;

import com.rocksolidapps.core.api.genre.Genre;
import com.rocksolidapps.core.api.genre.Genres;
import com.rocksolidapps.core.ui.base.BasePresenter;
import com.rocksolidapps.core.uithread.UiThread;

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
    private final static String NO_SORT = null;

    private final MoviesUseCase moviesUseCase;
    private final GenreMoviesUseCase genreMoviesUseCase;
    private final List<DiscoverMovieModelView> discoverMovieList = new ArrayList<>();
    private Genres genres;
    private int genreId = GENRE_INVALID_ID;
    private String sort = NO_SORT;
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
                }, throwable -> execute(discoverMovieUi -> discoverMovieUi.displayError(throwable.toString())));
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
        moviesUseCase.loadDiscoverMovie(page, genreIds, sort)
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
        execute(DiscoverMovieUi::notifyDataChange);
        page = DISCOVER_FIRST_PAGE;
    }

    private void showDiscoverMovie(List<DiscoverMovieModelView> discoverMovieModelView) {
        discoverMovieList.addAll(discoverMovieModelView);
        execute(DiscoverMovieUi::notifyDataChange);
        isMovieLoading = false;
    }

    public void setAndLoadMoviesGenre(int genreId) {
        this.genreId = genreId;
        refreshDiscoverMovie();
    }

    public void setAndLoadMoviesSort(String sort) {
        this.sort = sort;
        refreshDiscoverMovie();
    }

    public void clearGenre() {
        setAndLoadMoviesGenre(GENRE_INVALID_ID);
    }

    public void clearSort() {
        setAndLoadMoviesSort(NO_SORT);
    }

    public List<DiscoverMovieModelView> getDiscoverMovie() {
        return discoverMovieList;
    }

    public List<Genre> getGenreList() {
        return genres.getGenres();
    }

    public List<String> getSortList() { // todo change this
        LinkedList<String> sortList = new LinkedList<>();
        sortList.add("popularity.asc");
        sortList.add("popularity.desc");
        sortList.add("release_date.asc");
        sortList.add("release_date.desc");
        sortList.add("revenue.asc");
        sortList.add("revenue.desc");
        sortList.add("primary_release_date.asc");
        sortList.add("primary_release_date.desc");
        sortList.add("original_title.asc");
        sortList.add("original_title.desc");
        sortList.add("vote_average.asc");
        sortList.add("vote_average.desc");
        sortList.add("vote_count.asc");
        sortList.add("vote_count.desc");
        return sortList;
    }
}