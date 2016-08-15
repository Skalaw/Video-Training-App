package com.skala.core.ui.moviedescription;

import java.util.List;

/**
 * @author Skała
 */
public class MovieDescriptionModelView {
    private final int id;
    private final String title;
    private final String description;
    private final String releaseDate;
    private final Double voteAverage;
    private final String urlImageBackdrop;
    private final String urlImagePoster;
    private final List<VideosModelView> videosModelViews;

    public MovieDescriptionModelView(int id, String title, String description, String releaseDate, Double voteAverage, String urlImageBackdrop,
                                     String urlImagePoster, List<VideosModelView> videosModelViews) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.urlImageBackdrop = urlImageBackdrop;
        this.urlImagePoster = urlImagePoster;
        this.videosModelViews = videosModelViews;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getUrlImageBackdrop() {
        return urlImageBackdrop;
    }

    public String getUrlImagePoster() {
        return urlImagePoster;
    }

    public List<VideosModelView> getVideosModelViews() {
        return videosModelViews;
    }
}
