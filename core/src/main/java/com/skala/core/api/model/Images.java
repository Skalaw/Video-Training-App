package com.skala.core.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Images {

    @SerializedName("base_url")
    @Expose
    private String baseUrl;
    @SerializedName("secure_base_url")
    @Expose
    private String secureBaseUrl;
    @SerializedName("backdrop_sizes")
    @Expose
    private List<String> backdropSizes = new ArrayList<String>();
    @SerializedName("logo_sizes")
    @Expose
    private List<String> logoSizes = new ArrayList<String>();
    @SerializedName("poster_sizes")
    @Expose
    private List<String> posterSizes = new ArrayList<String>();
    @SerializedName("profile_sizes")
    @Expose
    private List<String> profileSizes = new ArrayList<String>();
    @SerializedName("still_sizes")
    @Expose
    private List<String> stillSizes = new ArrayList<String>();

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Images images = (Images) o;

        if (baseUrl != null ? !baseUrl.equals(images.baseUrl) : images.baseUrl != null) return false;
        if (secureBaseUrl != null ? !secureBaseUrl.equals(images.secureBaseUrl) : images.secureBaseUrl != null) return false;
        if (backdropSizes != null ? !backdropSizes.equals(images.backdropSizes) : images.backdropSizes != null) return false;
        if (logoSizes != null ? !logoSizes.equals(images.logoSizes) : images.logoSizes != null) return false;
        if (posterSizes != null ? !posterSizes.equals(images.posterSizes) : images.posterSizes != null) return false;
        if (profileSizes != null ? !profileSizes.equals(images.profileSizes) : images.profileSizes != null) return false;
        if (stillSizes != null ? !stillSizes.equals(images.stillSizes) : images.stillSizes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = baseUrl != null ? baseUrl.hashCode() : 0;
        result = 31 * result + (secureBaseUrl != null ? secureBaseUrl.hashCode() : 0);
        result = 31 * result + (backdropSizes != null ? backdropSizes.hashCode() : 0);
        result = 31 * result + (logoSizes != null ? logoSizes.hashCode() : 0);
        result = 31 * result + (posterSizes != null ? posterSizes.hashCode() : 0);
        result = 31 * result + (profileSizes != null ? profileSizes.hashCode() : 0);
        result = 31 * result + (stillSizes != null ? stillSizes.hashCode() : 0);
        return result;
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
