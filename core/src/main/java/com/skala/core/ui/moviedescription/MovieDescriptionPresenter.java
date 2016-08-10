package com.skala.core.ui.moviedescription;

import com.skala.core.api.net.CallApi;
import com.skala.core.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author Skała
 */
public class MovieDescriptionPresenter extends BasePresenter<MovieDescriptionUi> {
    private final MovieDescriptionUseCase movieDescriptionUseCase;
    private int movieId;

    @Inject
    public MovieDescriptionPresenter(MovieDescriptionUseCase movieDescriptionUseCase) {
        this.movieDescriptionUseCase = movieDescriptionUseCase;
    }

    @Override
    protected void onFirstUiAttachment() {
        loadVideoDescription();
    }

    public void loadVideoDescription() {
        movieDescriptionUseCase.loadInfoMovie(new CallApi<MovieDescriptionModelView, String>() {
            @Override
            public void onSuccess(MovieDescriptionModelView movieDescriptionModelViews) {
                execute(ui -> ui.displayMovieDescription(movieDescriptionModelViews));
            }

            @Override
            public void onFailed(String message) {
                execute(ui -> ui.displayError(message));
            }
        }, movieId);
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
