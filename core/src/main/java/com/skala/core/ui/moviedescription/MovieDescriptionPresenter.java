package com.skala.core.ui.moviedescription;

import com.skala.core.ui.base.BasePresenter;
import com.skala.core.uithread.UiThread;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.schedulers.Schedulers;

/**
 * @author Skała
 */
public class MovieDescriptionPresenter extends BasePresenter<MovieDescriptionUi> {
    private final MovieDescriptionUseCase movieDescriptionUseCase;
    private final List<VideosModelView> videosList = new ArrayList<>();
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
        movieDescriptionUseCase.loadInfoMovie(movieId)
                .observeOn(UiThread.uiScheduler())
                .subscribeOn(Schedulers.io())
                .subscribe(movieDescriptionModelView -> execute(movieDescriptionUi -> showVideoDescription(movieDescriptionUi, movieDescriptionModelView)),
                        throwable -> execute(ui1 -> ui1.displayError(throwable.toString())));
    }

    private void showVideoDescription(MovieDescriptionUi movieDescriptionUi, MovieDescriptionModelView movieDescriptionModelView) {
        videosList.addAll(movieDescriptionModelView.getVideosModelViews());
        movieDescriptionUi.displayMovieDescription(movieDescriptionModelView);

        movieDescriptionUseCase.loadVideosYoutubeInfo(videosList)
                .observeOn(UiThread.uiScheduler())
                .subscribeOn(Schedulers.io())
                .subscribe(videosModelViews -> execute(MovieDescriptionUi::notifyDataSetChanged),
                        throwable -> execute(ui -> ui.displayError(throwable.toString())));
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public List<VideosModelView> getVideosList() {
        return videosList;
    }
}
