package com.skala.core.ui.moviedescription;

import com.skala.core.api.model.ConfigurationApi;
import com.skala.core.api.model.Images;
import com.skala.core.api.model.MovieInfo;
import com.skala.core.api.model.YoutubeVideoInfo;
import com.skala.core.api.model.movievideos.MovieVideo;
import com.skala.core.api.model.movievideos.MovieVideoPages;
import com.skala.core.api.repository.ConfigurationRepository;
import com.skala.core.api.repository.VideoRepository;
import com.skala.core.api.repository.YoutubeRepository;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Skała
 */
@Singleton
public class MovieDescriptionUseCase {
    private static final int SIZE_IMAGE_BACKDROP = 2; // TODO: delete this
    private static final int SIZE_IMAGE_POSTER = 4; // TODO: delete this
    private static final String YOUTUBE = "YouTube";
    private static final String YOUTUBE_ENDPOINT = "https://www.youtube.com/watch?v=";

    private final VideoRepository videoRepository;
    private final ConfigurationRepository configurationRepository;
    private final YoutubeRepository youtubeRepository;

    @Inject
    public MovieDescriptionUseCase(VideoRepository videoRepository, ConfigurationRepository configurationRepository, YoutubeRepository youtubeRepository) {
        this.videoRepository = videoRepository;
        this.configurationRepository = configurationRepository;
        this.youtubeRepository = youtubeRepository;
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
        String urlBackdrop = secureBaseUrl + images.getBackdropSizes().get(SIZE_IMAGE_BACKDROP) + movieInfo.getBackdropPath();
        String urlPoster = secureBaseUrl + images.getPosterSizes().get(SIZE_IMAGE_POSTER) + movieInfo.getPosterPath();
        List<VideosModelView> videosYoutube = getVideosYoutube(movieVideoPages.getMovieVideos());

        return new MovieDescriptionModelView(movieId, movieInfo.getTitle(), movieInfo.getOverview(), movieInfo.getReleaseDate(), movieInfo.getVoteAverage(),
                urlBackdrop, urlPoster, videosYoutube);
    }

    private List<VideosModelView> getVideosYoutube(List<MovieVideo> movieVideos) {
        List<VideosModelView> movieVideosYoutube = new LinkedList<>();
        int size = movieVideos.size();
        for (int i = 0; i < size; i++) {
            MovieVideo movieVideo = movieVideos.get(i);
            if (YOUTUBE.equals(movieVideo.getSite())) {
                movieVideosYoutube.add(new VideosModelView(movieVideo.getId(), YOUTUBE_ENDPOINT + movieVideo.getKey(), movieVideo.getType()));
            }
        }
        return movieVideosYoutube;
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