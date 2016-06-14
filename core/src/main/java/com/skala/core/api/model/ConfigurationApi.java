package com.skala.core.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ConfigurationApi {

    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("change_keys")
    @Expose
    private List<String> changeKeys = new ArrayList<String>();

    /**
     * @return The images
     */
    public Images getImages() {
        return images;
    }

    /**
     * @param images The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

    /**
     * @return The changeKeys
     */
    public List<String> getChangeKeys() {
        return changeKeys;
    }

    /**
     * @param changeKeys The change_keys
     */
    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigurationApi that = (ConfigurationApi) o;

        if (images != null ? !images.equals(that.images) : that.images != null) return false;
        if (changeKeys != null ? !changeKeys.equals(that.changeKeys) : that.changeKeys != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = images != null ? images.hashCode() : 0;
        result = 31 * result + (changeKeys != null ? changeKeys.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConfigurationApi{" +
                "images=" + images +
                ", changeKeys=" + changeKeys +
                '}';
    }
}
