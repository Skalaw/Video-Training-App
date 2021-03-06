package com.rocksolidapps.core.ui.moviedescription;

import com.rocksolidapps.core.api.model.ConfigurationApi;
import com.rocksolidapps.core.api.model.Images;
import com.rocksolidapps.core.api.model.MovieInfo;
import com.rocksolidapps.core.api.model.YoutubeVideoInfo;
import com.rocksolidapps.core.api.model.movievideos.MovieVideo;
import com.rocksolidapps.core.api.model.movievideos.MovieVideoPages;
import com.rocksolidapps.core.api.repository.ConfigurationRepository;
import com.rocksolidapps.core.api.repository.VideoRepository;
import com.rocksolidapps.core.api.repository.YoutubeRepository;
import com.rocksolidapps.core.ui.ScreenSize;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Skała
 */
@Singleton
public class MovieDescriptionUseCase {
    private static final String YOUTUBE = "YouTube";
    private static final String YOUTUBE_ENDPOINT = "https://www.youtube.com/watch?v=";

    private final VideoRepository videoRepository;
    private final ConfigurationRepository configurationRepository;
    private final YoutubeRepository youtubeRepository;
    private final ScreenSize screenSize;

    @Inject
    public MovieDescriptionUseCase(VideoRepository videoRepository, ConfigurationRepository configurationRepository,
                                   YoutubeRepository youtubeRepository, ScreenSize screenSize) {
        this.videoRepository = videoRepository;
        this.configurationRepository = configurationRepository;
        this.youtubeRepository = youtubeRepository;
        this.screenSize = screenSize;
    }

    public Observable<MovieDescriptionModelView> loadInfoMovie(int movieId) {
        return configurationRepository.getConfiguration()
                .flatMap(configurationApi -> videoRepository.getMovieInfo(movieId)
                        .zipWith(loadVideosMovie(movieId), (movieInfo, movieVideoPages) -> {
                            return parseMovieDescriptionModelView(configurationApi, movieInfo, movieVideoPages, movieId);
                        }));
    }

    private Observable<MovieVideoPages> loadVideosMovie(int movieId) {
        return videoRepository.getMovieVideos(movieId);
    }

    private MovieDescriptionModelView parseMovieDescriptionModelView(ConfigurationApi configurationApi, MovieInfo movieInfo, MovieVideoPages movieVideoPages,
                                                                     int movieId) {
        Images images = configurationApi.getImages();
        String secureBaseUrl = images.getSecureBaseUrl();
        String urlBackdrop = secureBaseUrl + screenSize.getBackdropSize(images.getBackdropSizes()) + movieInfo.getBackdropPath();
        String urlPoster = secureBaseUrl + screenSize.getPosterSize(images.getPosterSizes()) + movieInfo.getPosterPath();
        List<VideosModelView> videosYoutube = getVideosYoutube(movieVideoPages.getMovieVideos());

        return new MovieDescriptionModelView(movieId, movieInfo.getTitle(), movieInfo.getOverview(), movieInfo.getReleaseDate(), movieInfo.getVoteAverage(),
                urlBackdrop, urlPoster, videosYoutube);
    }

    private List<VideosModelView> getVideosYoutube(List<MovieVideo> movieVideos) {
        return Observable.from(movieVideos)
                .filter(movieVideo -> YOUTUBE.equals(movieVideo.getSite()))
                .map(this::createVideosModelView)
                .toList()
                .toBlocking()
                .first();
    }

    private VideosModelView createVideosModelView(MovieVideo movieVideo) {
        return new VideosModelView(movieVideo.getId(), YOUTUBE_ENDPOINT + movieVideo.getKey(), movieVideo.getType());
    }

    public Observable<List<VideosModelView>> loadVideosYoutubeInfo(List<VideosModelView> videosModels) {
        return Observable.from(videosModels)
                .flatMap(this::getVideoYoutubeInfo)
                .toList();
    }

    public Observable<VideosModelView> getVideoYoutubeInfo(VideosModelView videosModelView) {
        return youtubeRepository.getYoutubeVideoInfo(videosModelView.getUrl())
                .map(youtubeVideoInfo -> updateVideosModel(videosModelView, youtubeVideoInfo));
    }

    private VideosModelView updateVideosModel(VideosModelView videosModelView, YoutubeVideoInfo youtubeVideoInfo) {
        videosModelView.setTitle(youtubeVideoInfo.getTitle());
        videosModelView.setThumbnailUrl(youtubeVideoInfo.getThumbnailUrl());
        return videosModelView;
    }
}