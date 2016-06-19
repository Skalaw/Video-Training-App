package com.skala.core.ui.DiscoverMovie;

/**
 * @author Skala
 */
public class DiscoverMovieModelView {
    private String title;
    private String description;
    private String urlImage;
    private String releaseDate;

    public DiscoverMovieModelView(String title, String description, String urlImage, String releaseDate) {
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
