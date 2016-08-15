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
    private final DiscoverMovieUseCase discoverMovieUseCase;
    private final List<DiscoverMovieModelView> discoverMovieList = new ArrayList<>();

    @Inject
    public DiscoverMoviePresenter(DiscoverMovieUseCase discoverMovieUseCase) {
        this.discoverMovieUseCase = discoverMovieUseCase;
    }

    @Override
    protected void onFirstUiAttachment() {
        loadDiscoverMovie();
    }

    public void loadDiscoverMovie() {
        discoverMovieUseCase.loadDiscoverMovie()
                .observeOn(UiThread.uiScheduler())
                .subscribeOn(Schedulers.io())
                .subscribe(this::showDiscoverMovie, throwable -> {
                    execute(ui -> ui.displayError(throwable.toString()));
                });
    }

    private void showDiscoverMovie(List<DiscoverMovieModelView> discoverMovieModelView) {
        discoverMovieList.clear();
        discoverMovieList.addAll(discoverMovieModelView);
        execute(DiscoverMovieUi::notifyDataChange);
    }

    public List<DiscoverMovieModelView> getDiscoverMovie() {
        return discoverMovieList;
    }
}