package com.skala.core.ui.moviedescription;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.Images;
import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.movievideos.MovieVideoPages;
import com.skala.core.api.net.CallApi;
import com.skala.core.api.repository.ConfigurationRepository;
import com.skala.core.api.repository.VideoRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Skała
 */
@Singleton
public class MovieDescriptionUseCase {
    private static final int SIZE_IMAGE_BACKDROP = 2; // TODO: delete this
    private static final int SIZE_IMAGE_POSTER = 4; // TODO: delete this

    private final VideoRepository videoRepository;
    private final ConfigurationRepository configurationRepository;

    @Inject
    public MovieDescriptionUseCase(VideoRepository videoRepository, ConfigurationRepository configurationRepository) {
        this.videoRepository = videoRepository;
        this.configurationRepository = configurationRepository;
    }

    public void loadInfoMovie(CallApi<MovieDescriptionModelView, String> callApiResponse, int movieId) {
        configurationRepository.getConfiguration(new CallApi<ConfigurationApi, String>() {
            @Override
            public void onSuccess(ConfigurationApi configurationApi) {
                loadInfoMovie(callApiResponse, movieId, configurationApi);
                //loadVideosMovie(callApiResponse, movieId); // todo: add implementation / ignore this at the moment
            }

            @Override
            public void onFailed(String error) {
                callApiResponse.onFailed(error);
            }
        });
    }

    private void loadInfoMovie(CallApi<MovieDescriptionModelView, String> callApiResponse, int movieId, ConfigurationApi configurationApi) {
        videoRepository.getMovieInfo(new CallApi<MovieInfo, String>() {
            @Override
            public void onSuccess(MovieInfo movieInfo) {
                Images images = configurationApi.getImages();
                String secureBaseUrl = images.getSecureBaseUrl();
                String urlBackdrop = secureBaseUrl + images.getBackdropSizes().get(SIZE_IMAGE_BACKDROP) + movieInfo.getBackdropPath();
                String urlPoster = secureBaseUrl + images.getPosterSizes().get(SIZE_IMAGE_POSTER) + movieInfo.getPosterPath();

                MovieDescriptionModelView movieDescription = new MovieDescriptionModelView(movieId, movieInfo.getTitle(), movieInfo.getOverview(),
                        movieInfo.getReleaseDate(), movieInfo.getVoteAverage(), urlBackdrop, urlPoster);

                callApiResponse.onSuccess(movieDescription);
            }

            @Override
            public void onFailed(String error) {
                callApiResponse.onFailed(error);
            }
        }, movieId);
    }

    private void loadVideosMovie(CallApi<MovieDescriptionModelView, String> callApiResponse, int movieId) {
        videoRepository.getMovieVideos(new CallApi<MovieVideoPages, String>() {
            @Override
            public void onSuccess(MovieVideoPages movieVideoPages) {
                // todo: add implementation / ignore this at the moment
            }

            @Override
            public void onFailed(String error) {
                callApiResponse.onFailed(error);
            }
        }, movieId);
    }
}