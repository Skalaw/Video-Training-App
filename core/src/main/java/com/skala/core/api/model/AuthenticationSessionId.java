package com.skala.core.api.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class AuthenticationSessionId {

    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("success")
    @Expose
    private Boolean success;

    /**
     *
     * @return
     * The sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     *
     * @param sessionId
     * The session_id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     *
     * @return
     * The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     * The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationSessionId that = (AuthenticationSessionId) o;

        if (sessionId != null ? !sessionId.equals(that.sessionId) : that.sessionId != null) return false;
        if (success != null ? !success.equals(that.success) : that.success != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionId != null ? sessionId.hashCode() : 0;
        result = 31 * result + (success != null ? success.hashCode() : 0);
        return result;
    }
}