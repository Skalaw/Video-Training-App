package com.skala.core.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Skala
 */
public class Images {
    @SerializedName("base_url")
    @Expose
    private String baseUrl;
    @SerializedName("secure_base_url")
    @Expose
    private String secureBaseUrl;
    @SerializedName("backdrop_sizes")
    @Expose
    private List<String> backdropSizes = new ArrayList<>();
    @SerializedName("logo_sizes")
    @Expose
    private List<String> logoSizes = new ArrayList<>();
    @SerializedName("poster_sizes")
    @Expose
    private List<String> posterSizes = new ArrayList<>();
    @SerializedName("profile_sizes")
    @Expose
    private List<String> profileSizes = new ArrayList<>();
    @SerializedName("still_sizes")
    @Expose
    private List<String> stillSizes = new ArrayList<>();

    /**
     * @return The baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl The base_url
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return The secureBaseUrl
     */
    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    /**
     * @param secureBaseUrl The secure_base_url
     */
    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }

    /**
     * @return The backdropSizes
     */
    public List<String> getBackdropSizes() {
        return backdropSizes;
    }

    /**
     * @param backdropSizes The backdrop_sizes
     */
    public void setBackdropSizes(List<String> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    /**
     * @return The logoSizes
     */
    public List<String> getLogoSizes() {
        return logoSizes;
    }

    /**
     * @param logoSizes The logo_sizes
     */
    public void setLogoSizes(List<String> logoSizes) {
        this.logoSizes = logoSizes;
    }

    /**
     * @return The posterSizes
     */
    public List<String> getPosterSizes() {
        return posterSizes;
    }

    /**
     * @param posterSizes The poster_sizes
     */
    public void setPosterSizes(List<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    /**
     * @return The profileSizes
     */
    public List<String> getProfileSizes() {
        return profileSizes;
    }

    /**
     * @param profileSizes The profile_sizes
     */
    public void setProfileSizes(List<String> profileSizes) {
        this.profileSizes = profileSizes;
    }

    /**
     * @return The stillSizes
     */
    public List<String> getStillSizes() {
        return stillSizes;
    }

    /**
     * @param stillSizes The still_sizes
     */
    public void setStillSizes(List<String> stillSizes) {
        this.stillSizes = stillSizes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Images images = (Images) o;
        return Objects.equals(baseUrl, images.baseUrl)
                && Objects.equals(secureBaseUrl, images.secureBaseUrl)
                && Objects.equals(backdropSizes, images.backdropSizes)
                && Objects.equals(logoSizes, images.logoSizes)
                && Objects.equals(posterSizes, images.posterSizes)
                && Objects.equals(profileSizes, images.profileSizes)
                && Objects.equals(stillSizes, images.stillSizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseUrl, secureBaseUrl, backdropSizes, logoSizes, posterSizes, profileSizes, stillSizes);
    }

    @Override
    public String toString() {
        return "Images{"
                + "baseUrl='" + baseUrl + '\''
                + ", secureBaseUrl='" + secureBaseUrl + '\''
                + ", backdropSizes=" + backdropSizes
                + ", logoSizes=" + logoSizes
                + ", posterSizes=" + posterSizes
                + ", profileSizes=" + profileSizes
                + ", stillSizes=" + stillSizes
                + '}';
    }
}
