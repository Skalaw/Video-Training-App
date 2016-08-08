package com.skala.core.ui.moviedescription;

import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.movievideos.MovieVideos;
import com.skala.core.api.net.CallApi;
import com.skala.core.api.repository.VideoRepository;
import com.skala.core.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author Skała
 */
public class MovieDescriptionPresenter extends BasePresenter<MovieDescriptionUi> {
    private final VideoRepository videoRepository;
    private int movieId;

    @Inject
    public MovieDescriptionPresenter(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    protected void onFirstUiAttachment() {
        videoRepository.getMovieInfo(new CallApi<MovieInfo, String>() {
            @Override
            public void onSuccess(MovieInfo movieInfo) {
                execute(ui -> ui.displayError(movieInfo.getOriginalTitle())); // todo remove
            }

            @Override
            public void onFailed(String error) {
                execute(ui -> ui.displayError(error));
            }
        }, movieId);

        videoRepository.getMovieVideos(new CallApi<MovieVideos, String>() {
            @Override
            public void onSuccess(MovieVideos movieVideos) {
                // empty
            }

            @Override
            public void onFailed(String error) {
                execute(ui -> ui.displayError(error));
            }
        }, movieId);
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
