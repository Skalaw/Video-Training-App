
package com.skala.core.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * @author Skala
 */
public class AuthenticationToken {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("expires_at")
    @Expose
    private String expiresAt;
    @SerializedName("request_token")
    @Expose
    private String requestToken;

    /**
     * @return The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return The expiresAt
     */
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * @param expiresAt The expires_at
     */
    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * @return The requestToken
     */
    public String getRequestToken() {
        return requestToken;
    }

    /**
     * @param requestToken The request_token
     */
    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthenticationToken that = (AuthenticationToken) o;
        return Objects.equals(success, that.success)
                && Objects.equals(expiresAt, that.expiresAt)
                && Objects.equals(requestToken, that.requestToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, expiresAt, requestToken);
    }
}
