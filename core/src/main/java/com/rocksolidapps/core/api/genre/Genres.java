package com.rocksolidapps.core.api.genre;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Skała
 */
public class Genres {
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = new ArrayList<>();

    /**
     * @return The genres
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * @param genres The genres
     */
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}