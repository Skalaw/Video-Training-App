package com.skala.core.ui.discovermovie;

import com.skala.core.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.functions.Action1;

/**
 * @author Skala
 */
@Singleton
public class DiscoverMoviePresenter extends BasePresenter<DiscoverMovieUi> {
    private final DiscoverMovieListUseCase discoverMovieListUseCase;
    private final List<DiscoverMovieModelView> discoverMovieList = new ArrayList<>();

    @Inject
    public DiscoverMoviePresenter(DiscoverMovieListUseCase discoverMovieListUseCase) {
        this.discoverMovieListUseCase = discoverMovieListUseCase;
    }

    @Override
    protected void onFirstUiAttachment() {
        loadDiscoverMovie();
    }

    public void loadDiscoverMovie() {
        discoverMovieListUseCase.loadDiscoverMovie().subscribe(new Action1<List<DiscoverMovieModelView>>() {
            @Override
            public void call(List<DiscoverMovieModelView> discoverMovieModelView) {
                discoverMovieList.clear();
                discoverMovieList.addAll(discoverMovieModelView);

                execute(DiscoverMovieUi::notifyDataChange);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                execute(ui -> ui.displayError(throwable.toString()));
            }
        });
    }

    public List<DiscoverMovieModelView> getDiscoverMovie() {
        return discoverMovieList;
    }
}