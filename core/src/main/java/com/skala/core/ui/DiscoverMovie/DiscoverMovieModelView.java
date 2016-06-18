package com.skala.core.ui.DiscoverMovie;

/**
 * @author Skala
 */
public class DiscoverMovieModelView {
    private String title;
    private String description;
    private String urlImage;

    public DiscoverMovieModelView(String title, String description, String urlImage) {
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
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
}
