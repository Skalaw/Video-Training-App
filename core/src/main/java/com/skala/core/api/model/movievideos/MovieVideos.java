package com.skala.core.api.model.movievideos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Skala
 */

public class MovieVideos {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<>();

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

}