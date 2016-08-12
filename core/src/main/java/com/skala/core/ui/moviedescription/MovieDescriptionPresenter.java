package com.skala.core.ui.moviedescription;

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
        movieDescriptionUseCase.loadInfoMovie(movieId).subscribe(movieDescriptionModelView -> {
            execute(ui1 -> ui1.displayMovieDescription(movieDescriptionModelView));
        }, throwable -> {
            execute(ui1 -> ui1.displayError(throwable.toString()));
        });
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
