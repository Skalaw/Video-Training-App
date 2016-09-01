package com.rocksolidapps.core.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * @author Skala
 */
public class AuthenticationSessionId {
    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("success")
    @Expose
    private Boolean success;

    /**
     * @return The sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId The session_id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthenticationSessionId that = (AuthenticationSessionId) o;
        return Objects.equals(sessionId, that.sessionId)
                && Objects.equals(success, that.success);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, success);
    }
}