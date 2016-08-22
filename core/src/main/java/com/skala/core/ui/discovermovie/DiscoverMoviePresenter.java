package com.skala.core.ui.discovermovie;

import com.skala.core.ui.base.BasePresenter;
import com.skala.core.uithread.UiThread;

import java.util.ArrayList;
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

    private final DiscoverMovieUseCase discoverMovieUseCase;
    private final List<DiscoverMovieModelView> discoverMovieList = new ArrayList<>();
    private int page = DISCOVER_FIRST_PAGE;
    private boolean isMovieLoading = false;
    private boolean isLastPage = false; // todo check when isLastPage

    @Inject
    public DiscoverMoviePresenter(DiscoverMovieUseCase discoverMovieUseCase) {
        this.discoverMovieUseCase = discoverMovieUseCase;
    }

    @Override
    protected void onFirstUiAttachment() {
        loadDiscoverMovie(DISCOVER_FIRST_PAGE);
    }

    public void loadNextDiscoverMovie() {
        if (isLoadMoviesAvailable()) {
            page++;
            loadDiscoverMovie(page);
        }
    }

    private boolean isLoadMoviesAvailable() {
        return !isMovieLoading && !isLastPage;
    }

    private void loadDiscoverMovie(int page) {
        isMovieLoading = true;
        discoverMovieUseCase.loadDiscoverMovie(page)
                .observeOn(UiThread.uiScheduler())
                .subscribeOn(Schedulers.io())
                .subscribe(this::showDiscoverMovie, throwable -> {
                    execute(ui -> ui.displayError(throwable.toString()));
                    isMovieLoading = false;
                });
    }

    public void refreshDiscoverMovie() {
        isMovieLoading = true;
        discoverMovieUseCase.loadDiscoverMovie(DISCOVER_FIRST_PAGE)
                .observeOn(UiThread.uiScheduler())
                .subscribeOn(Schedulers.io())
                .subscribe(discoverMovieModelViews -> {
                    discoverMovieList.clear();
                    page = DISCOVER_FIRST_PAGE;
                    showDiscoverMovie(discoverMovieModelViews);
                }, throwable -> {
                    execute(ui -> ui.displayError(throwable.toString()));
                    isMovieLoading = false;
                });
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