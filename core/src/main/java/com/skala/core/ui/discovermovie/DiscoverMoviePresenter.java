package com.skala.core.ui.discovermovie;

import com.skala.core.api.net.CallApi;
import com.skala.core.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

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
        discoverMovieListUseCase.loadDiscoverMovie(new CallApi<List<DiscoverMovieModelView>, String>() {
            @Override
            public void onSuccess(List<DiscoverMovieModelView> discoverMovieModelView) {
                discoverMovieList.clear();
                discoverMovieList.addAll(discoverMovieModelView);

                execute(DiscoverMovieUi::notifyDataChange);
            }

            @Override
            public void onFailed(String error) {
                execute(ui -> ui.displayError(error));
            }
        });
    }

    public List<DiscoverMovieModelView> getDiscoverMovie() {
        return discoverMovieList;
    }
}
