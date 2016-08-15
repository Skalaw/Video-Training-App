package com.skala.core.ui.moviedescription;

/**
 * @author Skala
 */
public class VideosModelView {
    private final String id;
    private String title = null;
    private final String url;
    private String thumbnailUrl = null;
    private final String type;

    public VideosModelView(String id, String url, String type) {
        this.id = id;
        this.url = url;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getType() {
        return type;
    }
}
