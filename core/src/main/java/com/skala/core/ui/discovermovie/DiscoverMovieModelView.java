package com.skala.core.ui.discovermovie;

/**
 * @author Skala
 */
public class DiscoverMovieModelView {
    private final int id;
    private final String title;
    private final String description;
    private final String urlImage;
    private final String releaseDate;

    public DiscoverMovieModelView(int id, String title, String description, String urlImage, String releaseDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
        this.releaseDate = releaseDate;
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

    public String getUrlImage() {
        return urlImage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
