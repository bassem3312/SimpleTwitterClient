
package com.eventtus.simpletwitterclient.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class FollowersResult implements Serializable {

    @SerializedName("users")
    private List<TwitterUser> users = new ArrayList<TwitterUser>();
    @SerializedName("next_cursor")
    private Long nextCursor;
    @SerializedName("next_cursor_str")
    private String nextCursorStr;
    @SerializedName("previous_cursor")
    private Long previousCursor;
    @SerializedName("previous_cursor_str")
    private String previousCursorStr;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The users
     */
    //@JsonProperty("users")
    public List<TwitterUser> getUsers() {
        return users;
    }

    /**
     * 
     * @param users
     *     The users
     */
    //@JsonProperty("users")
    public void setUsers(List<TwitterUser> users) {
        this.users = users;
    }

    /**
     * 
     * @return
     *     The nextCursor
     */
    //@JsonProperty("next_cursor")
    public Long getNextCursor() {
        return nextCursor;
    }

    /**
     * 
     * @param nextCursor
     *     The next_cursor
     */
    //@JsonProperty("next_cursor")
    public void setNextCursor(Long nextCursor) {
        this.nextCursor = nextCursor;
    }

    /**
     * 
     * @return
     *     The nextCursorStr
     */
    //@JsonProperty("next_cursor_str")
    public String getNextCursorStr() {
        return nextCursorStr;
    }

    /**
     * 
     * @param nextCursorStr
     *     The next_cursor_str
     */
    //@JsonProperty("next_cursor_str")
    public void setNextCursorStr(String nextCursorStr) {
        this.nextCursorStr = nextCursorStr;
    }

    /**
     * 
     * @return
     *     The previousCursor
     */
    //@JsonProperty("previous_cursor")
    public Long getPreviousCursor() {
        return previousCursor;
    }

    /**
     * 
     * @param previousCursor
     *     The previous_cursor
     */
    //@JsonProperty("previous_cursor")
    public void setPreviousCursor(Long previousCursor) {
        this.previousCursor = previousCursor;
    }

    /**
     * 
     * @return
     *     The previousCursorStr
     */
    //@JsonProperty("previous_cursor_str")
    public String getPreviousCursorStr() {
        return previousCursorStr;
    }

    /**
     * 
     * @param previousCursorStr
     *     The previous_cursor_str
     */
    //@JsonProperty("previous_cursor_str")
    public void setPreviousCursorStr(String previousCursorStr) {
        this.previousCursorStr = previousCursorStr;
    }

    //@JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    //@JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
