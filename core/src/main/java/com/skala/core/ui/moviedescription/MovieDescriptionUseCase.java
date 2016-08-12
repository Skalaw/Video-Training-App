package com.skala.core.ui.moviedescription;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.Images;
import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.movievideos.MovieVideoPages;
import com.skala.core.api.repository.ConfigurationRepository;
import com.skala.core.api.repository.VideoRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

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

    public Observable<MovieDescriptionModelView> loadInfoMovie(int movieId) {
        return configurationRepository.getConfiguration().flatMap(new Func1<ConfigurationApi, Observable<MovieDescriptionModelView>>() {
            @Override
            public Observable<MovieDescriptionModelView> call(ConfigurationApi configurationApi) {
                return videoRepository.getMovieInfo(movieId)
                        .map(movieInfo -> parseMovieDescriptionModelView(configurationApi, movieInfo, movieId));
                //loadVideosMovie(callApiResponse, movieId); // todo: add implementation / ignore this at the moment
            }
        });
    }

    private MovieDescriptionModelView parseMovieDescriptionModelView(ConfigurationApi configurationApi, MovieInfo movieInfo, int movieId) {
        Images images = configurationApi.getImages();
        String secureBaseUrl = images.getSecureBaseUrl();
        String urlBackdrop = secureBaseUrl + images.getBackdropSizes().get(SIZE_IMAGE_BACKDROP) + movieInfo.getBackdropPath();
        String urlPoster = secureBaseUrl + images.getPosterSizes().get(SIZE_IMAGE_POSTER) + movieInfo.getPosterPath();

        return new MovieDescriptionModelView(movieId, movieInfo.getTitle(), movieInfo.getOverview(),
                movieInfo.getReleaseDate(), movieInfo.getVoteAverage(), urlBackdrop, urlPoster);
    }

    private Observable<MovieVideoPages> loadVideosMovie(int movieId) {
        return videoRepository.getMovieVideos(movieId);
    }
}