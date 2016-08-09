package com.skala.core.ui.moviedescription;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.movievideos.MovieVideos;
import com.skala.core.api.net.CallApi;
import com.skala.core.api.repository.ConfigurationRepository;
import com.skala.core.api.repository.VideoRepository;
import com.skala.core.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author Skała
 */
public class MovieDescriptionPresenter extends BasePresenter<MovieDescriptionUi> {
    private final VideoRepository videoRepository;
    private final ConfigurationRepository configurationRepository;
    private ConfigurationApi configurationApi;
    private int movieId;

    @Inject
    public MovieDescriptionPresenter(VideoRepository videoRepository, ConfigurationRepository configurationRepository) {
        this.videoRepository = videoRepository;
        this.configurationRepository = configurationRepository;
    }

    @Override
    protected void onFirstUiAttachment() {
        configurationRepository.getConfiguration(new CallApi<ConfigurationApi, String>() {

            @Override
            public void onSuccess(ConfigurationApi configurationApi) {
                MovieDescriptionPresenter.this.configurationApi = configurationApi;
            }

            @Override
            public void onFailed(String s) {

            }
        });


        videoRepository.getMovieInfo(new CallApi<MovieInfo, String>() { // todo: add usecase
            @Override
            public void onSuccess(MovieInfo movieInfo) {
                execute(ui -> ui.displayMovieInfo(movieInfo, configurationApi));
            }

            @Override
            public void onFailed(String error) {
                execute(ui -> ui.displayError(error));
            }
        }, movieId);

        videoRepository.getMovieVideos(new CallApi<MovieVideos, String>() {// todo: add usecase
            @Override
            public void onSuccess(MovieVideos movieVideos) {
                execute(ui -> ui.displayMovieVideos(movieVideos));
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
