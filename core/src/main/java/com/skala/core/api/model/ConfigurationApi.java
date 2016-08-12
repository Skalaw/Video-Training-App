package com.skala.core.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Skala
 */
public class ConfigurationApi {
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("change_keys")
    @Expose
    private List<String> changeKeys = new ArrayList<>();

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConfigurationApi that = (ConfigurationApi) o;
        return Objects.equals(images, that.images)
                && Objects.equals(changeKeys, that.changeKeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(images, changeKeys);
    }

    @Override
    public String toString() {
        return "ConfigurationApi{"
                + "images=" + images
                + ", changeKeys=" + changeKeys
                + '}';
    }
}
