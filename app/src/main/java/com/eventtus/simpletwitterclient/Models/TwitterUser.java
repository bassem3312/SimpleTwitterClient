package com.eventtus.simpletwitterclient.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//
public class TwitterUser implements Serializable{

    @SerializedName("id")
    private Long id;
    //@SerializedName("id_str")
    private String idStr;
    @SerializedName("name")
    private String name;
    @SerializedName("screen_name")
    private String screenName;
    @SerializedName("location")
    private String location;
    @SerializedName("profile_location")
    private Object profileLocation;
    @SerializedName("url")
    private Object url;
    @SerializedName("description")
    private String description;
    @SerializedName("protected")
    private Boolean _protected;
    @SerializedName("followers_count")
    private Long followersCount;
    @SerializedName("friends_count")
    private Long friendsCount;
    @SerializedName("listed_count")
    private Long listedCount;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("favourites_count")
    private Long favouritesCount;
    @SerializedName("utc_offset")
    private Long utcOffset;
    @SerializedName("time_zone")
    private String timeZone;
    @SerializedName("geo_enabled")
    private Boolean geoEnabled;
    @SerializedName("verified")
    private Boolean verified;
    @SerializedName("statuses_count")
    private Long statusesCount;
    @SerializedName("lang")
    private String lang;
    @SerializedName("contributors_enabled")
    private Boolean contributorsEnabled;
    @SerializedName("is_translator")
    private Boolean isTranslator;
    @SerializedName("is_translation_enabled")
    private Boolean isTranslationEnabled;
    @SerializedName("profile_background_color")
    private String profileBackgroundColor;
    @SerializedName("profile_background_image_url")
    private String profileBackgroundImageUrl;
    @SerializedName("profile_background_image_url_https")
    private String profileBackgroundImageUrlHttps;
    @SerializedName("profile_background_tile")
    private Boolean profileBackgroundTile;
    @SerializedName("profile_image_url")
    private String profileImageUrl;
    @SerializedName("profile_image_url_https")
    private String profileImageUrlHttps;
    @SerializedName("profile_link_color")
    private String profileLinkColor;
    @SerializedName("profile_sidebar_border_color")
    private String profileSidebarBorderColor;
    @SerializedName("profile_sidebar_fill_color")
    private String profileSidebarFillColor;
    @SerializedName("profile_text_color")
    private String profileTextColor;
    @SerializedName("profile_use_background_image")
    private Boolean profileUseBackgroundImage;
    @SerializedName("default_profile")
    private Boolean defaultProfile;
    @SerializedName("default_profile_image")
    private Boolean defaultProfileImage;
    @SerializedName("following")
    private Boolean following;
    @SerializedName("follow_request_sent")
    private Boolean followRequestSent;
    @SerializedName("notifications")
    private Boolean notifications;
    @SerializedName("muting")
    private Boolean muting;
    @SerializedName("profile_banner_url")
    private String profileBannerUrl;
    //@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private String email;

    /**
     * @return The id
     */
    //@SerializedName("id")
    public Long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    //@SerializedName("id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The idStr
     */
    //@SerializedName("id_str")
    public String getIdStr() {
        return idStr;
    }

    /**
     * @param idStr The id_str
     */
    //@SerializedName("id_str")
    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    /**
     * @return The name
     */
    //@SerializedName("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    //@SerializedName("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The screenName
     */
//    @SerializedName("screen_name")
    public String getScreenName() {
        return screenName;
    }

    /**
     * @param screenName The screen_name
     */
    //@SerializedName("screen_name")
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /**
     * @return The location
     */
    //@SerializedName("location")
    public String getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    //@SerializedName("location")
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return The profileLocation
     */
    //@SerializedName("profile_location")
    public Object getProfileLocation() {
        return profileLocation;
    }

    /**
     * @param profileLocation The profile_location
     */
    //@SerializedName("profile_location")
    public void setProfileLocation(Object profileLocation) {
        this.profileLocation = profileLocation;
    }

    /**
     * @return The url
     */
    //@SerializedName("url")
    public Object getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    //@SerializedName("url")
    public void setUrl(Object url) {
        this.url = url;
    }

    /**
     * @return The description
     */
    //@SerializedName("description")
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    //@SerializedName("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The _protected
     */
    //@SerializedName("protected")
    public Boolean getProtected() {
        return _protected;
    }

    /**
     * @param _protected The protected
     */
    //@SerializedName("protected")
    public void setProtected(Boolean _protected) {
        this._protected = _protected;
    }

    /**
     * @return The followersCount
     */
    //@SerializedName("followers_count")
    public Long getFollowersCount() {
        return followersCount;
    }

    /**
     * @param followersCount The followers_count
     */
    //@SerializedName("followers_count")
    public void setFollowersCount(Long followersCount) {
        this.followersCount = followersCount;
    }

    /**
     * @return The friendsCount
     */
    //@SerializedName("friends_count")
    public Long getFriendsCount() {
        return friendsCount;
    }

    /**
     * @param friendsCount The friends_count
     */
    //@SerializedName("friends_count")
    public void setFriendsCount(Long friendsCount) {
        this.friendsCount = friendsCount;
    }

    /**
     * @return The listedCount
     */
    //@SerializedName("listed_count")
    public Long getListedCount() {
        return listedCount;
    }

    /**
     * @param listedCount The listed_count
     */
    //@SerializedName("listed_count")
    public void setListedCount(Long listedCount) {
        this.listedCount = listedCount;
    }

    /**
     * @return The createdAt
     */
    //@SerializedName("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    //@SerializedName("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The favouritesCount
     */
    //@SerializedName("favourites_count")
    public Long getFavouritesCount() {
        return favouritesCount;
    }

    /**
     * @param favouritesCount The favourites_count
     */
    //@SerializedName("favourites_count")
    public void setFavouritesCount(Long favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    /**
     * @return The utcOffset
     */
    //@SerializedName("utc_offset")
    public Long getUtcOffset() {
        return utcOffset;
    }

    /**
     * @param utcOffset The utc_offset
     */
    //@SerializedName("utc_offset")
    public void setUtcOffset(Long utcOffset) {
        this.utcOffset = utcOffset;
    }

    /**
     * @return The timeZone
     */
    //@SerializedName("time_zone")
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * @param timeZone The time_zone
     */
    //@SerializedName("time_zone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @return The geoEnabled
     */
    //@SerializedName("geo_enabled")
    public Boolean getGeoEnabled() {
        return geoEnabled;
    }

    /**
     * @param geoEnabled The geo_enabled
     */
    //@SerializedName("geo_enabled")
    public void setGeoEnabled(Boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    /**
     * @return The verified
     */
    //@SerializedName("verified")
    public Boolean getVerified() {
        return verified;
    }

    /**
     * @param verified The verified
     */
    //@SerializedName("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    /**
     * @return The statusesCount
     */
    //@SerializedName("statuses_count")
    public Long getStatusesCount() {
        return statusesCount;
    }

    /**
     * @param statusesCount The statuses_count
     */
    //@SerializedName("statuses_count")
    public void setStatusesCount(Long statusesCount) {
        this.statusesCount = statusesCount;
    }

    /**
     * @return The lang
     */
    //@SerializedName("lang")
    public String getLang() {
        return lang;
    }

    /**
     * @param lang The lang
     */
    //@SerializedName("lang")
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return The contributorsEnabled
     */
    //@SerializedName("contributors_enabled")
    public Boolean getContributorsEnabled() {
        return contributorsEnabled;
    }

    /**
     * @param contributorsEnabled The contributors_enabled
     */
    //@SerializedName("contributors_enabled")
    public void setContributorsEnabled(Boolean contributorsEnabled) {
        this.contributorsEnabled = contributorsEnabled;
    }

    /**
     * @return The isTranslator
     */
    //@SerializedName("is_translator")
    public Boolean getIsTranslator() {
        return isTranslator;
    }

    /**
     * @param isTranslator The is_translator
     */
    //@SerializedName("is_translator")
    public void setIsTranslator(Boolean isTranslator) {
        this.isTranslator = isTranslator;
    }

    /**
     * @return The isTranslationEnabled
     */
    //@SerializedName("is_translation_enabled")
    public Boolean getIsTranslationEnabled() {
        return isTranslationEnabled;
    }

    /**
     * @param isTranslationEnabled The is_translation_enabled
     */
    //@SerializedName("is_translation_enabled")
    public void setIsTranslationEnabled(Boolean isTranslationEnabled) {
        this.isTranslationEnabled = isTranslationEnabled;
    }

    /**
     * @return The profileBackgroundColor
     */
    //@SerializedName("profile_background_color")
    public String getProfileBackgroundColor() {
        return profileBackgroundColor;
    }

    /**
     * @param profileBackgroundColor The profile_background_color
     */
    //@SerializedName("profile_background_color")
    public void setProfileBackgroundColor(String profileBackgroundColor) {
        this.profileBackgroundColor = profileBackgroundColor;
    }

    /**
     * @return The profileBackgroundImageUrl
     */
    //@SerializedName("profile_background_image_url")
    public String getProfileBackgroundImageUrl() {
        return profileBackgroundImageUrl;
    }

    /**
     * @param profileBackgroundImageUrl The profile_background_image_url
     */
    //@SerializedName("profile_background_image_url")
    public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl) {
        this.profileBackgroundImageUrl = profileBackgroundImageUrl;
    }

    /**
     * @return The profileBackgroundImageUrlHttps
     */
    //@SerializedName("profile_background_image_url_https")
    public String getProfileBackgroundImageUrlHttps() {
        return profileBackgroundImageUrlHttps;
    }

    /**
     * @param profileBackgroundImageUrlHttps The profile_background_image_url_https
     */
    //@SerializedName("profile_background_image_url_https")
    public void setProfileBackgroundImageUrlHttps(String profileBackgroundImageUrlHttps) {
        this.profileBackgroundImageUrlHttps = profileBackgroundImageUrlHttps;
    }

    /**
     * @return The profileBackgroundTile
     */
    //@SerializedName("profile_background_tile")
    public Boolean getProfileBackgroundTile() {
        return profileBackgroundTile;
    }

    /**
     * @param profileBackgroundTile The profile_background_tile
     */
    //@SerializedName("profile_background_tile")
    public void setProfileBackgroundTile(Boolean profileBackgroundTile) {
        this.profileBackgroundTile = profileBackgroundTile;
    }

    /**
     * @return The profileImageUrl
     */
    //@SerializedName("profile_image_url")
    public String getProfileImageUrl() {
        return profileImageUrl.replace("_normal", "");
    }

    /**
     * @param profileImageUrl The profile_image_url
     */
    //@SerializedName("profile_image_url")
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    /**
     * @return The profileImageUrlHttps
     */
    //@SerializedName("profile_image_url_https")
    public String getProfileImageUrlHttps() {
        return profileImageUrlHttps;
    }

    /**
     * @param profileImageUrlHttps The profile_image_url_https
     */
    //@SerializedName("profile_image_url_https")
    public void setProfileImageUrlHttps(String profileImageUrlHttps) {
        this.profileImageUrlHttps = profileImageUrlHttps;
    }

    /**
     * @return The profileLinkColor
     */
    //@SerializedName("profile_link_color")
    public String getProfileLinkColor() {
        return profileLinkColor;
    }

    /**
     * @param profileLinkColor The profile_link_color
     */
    //@SerializedName("profile_link_color")
    public void setProfileLinkColor(String profileLinkColor) {
        this.profileLinkColor = profileLinkColor;
    }

    /**
     * @return The profileSidebarBorderColor
     */
    //@SerializedName("profile_sidebar_border_color")
    public String getProfileSidebarBorderColor() {
        return profileSidebarBorderColor;
    }

    /**
     * @param profileSidebarBorderColor The profile_sidebar_border_color
     */
    //@SerializedName("profile_sidebar_border_color")
    public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
        this.profileSidebarBorderColor = profileSidebarBorderColor;
    }

    /**
     * @return The profileSidebarFillColor
     */
    //@SerializedName("profile_sidebar_fill_color")
    public String getProfileSidebarFillColor() {
        return profileSidebarFillColor;
    }

    /**
     * @param profileSidebarFillColor The profile_sidebar_fill_color
     */
    //@SerializedName("profile_sidebar_fill_color")
    public void setProfileSidebarFillColor(String profileSidebarFillColor) {
        this.profileSidebarFillColor = profileSidebarFillColor;
    }

    /**
     * @return The profileTextColor
     */
    //@SerializedName("profile_text_color")
    public String getProfileTextColor() {
        return profileTextColor;
    }

    /**
     * @param profileTextColor The profile_text_color
     */
    //@SerializedName("profile_text_color")
    public void setProfileTextColor(String profileTextColor) {
        this.profileTextColor = profileTextColor;
    }

    /**
     * @return The profileUseBackgroundImage
     */
    //@SerializedName("profile_use_background_image")
    public Boolean getProfileUseBackgroundImage() {
        return profileUseBackgroundImage;
    }

    /**
     * @param profileUseBackgroundImage The profile_use_background_image
     */
    //@SerializedName("profile_use_background_image")
    public void setProfileUseBackgroundImage(Boolean profileUseBackgroundImage) {
        this.profileUseBackgroundImage = profileUseBackgroundImage;
    }

    /**
     * @return The defaultProfile
     */
    //@SerializedName("default_profile")
    public Boolean getDefaultProfile() {
        return defaultProfile;
    }

    /**
     * @param defaultProfile The default_profile
     */
    //@SerializedName("default_profile")
    public void setDefaultProfile(Boolean defaultProfile) {
        this.defaultProfile = defaultProfile;
    }

    /**
     * @return The defaultProfileImage
     */
    //@SerializedName("default_profile_image")
    public Boolean getDefaultProfileImage() {
        return defaultProfileImage;
    }

    /**
     * @param defaultProfileImage The default_profile_image
     */
    //@SerializedName("default_profile_image")
    public void setDefaultProfileImage(Boolean defaultProfileImage) {
        this.defaultProfileImage = defaultProfileImage;
    }

    /**
     * @return The following
     */
    //@SerializedName("following")
    public Boolean getFollowing() {
        return following;
    }

    /**
     * @param following The following
     */
    //@SerializedName("following")
    public void setFollowing(Boolean following) {
        this.following = following;
    }

    /**
     * @return The followRequestSent
     */
    //@SerializedName("follow_request_sent")
    public Boolean getFollowRequestSent() {
        return followRequestSent;
    }

    /**
     * @param followRequestSent The follow_request_sent
     */
    //@SerializedName("follow_request_sent")
    public void setFollowRequestSent(Boolean followRequestSent) {
        this.followRequestSent = followRequestSent;
    }

    /**
     * @return The notifications
     */
    //@SerializedName("notifications")
    public Boolean getNotifications() {
        return notifications;
    }

    /**
     * @param notifications The notifications
     */
    //@SerializedName("notifications")
    public void setNotifications(Boolean notifications) {
        this.notifications = notifications;
    }

    /**
     * @return The muting
     */
    //@SerializedName("muting")
    public Boolean getMuting() {
        return muting;
    }

    /**
     * @param muting The muting
     */
    //@SerializedName("muting")
    public void setMuting(Boolean muting) {
        this.muting = muting;
    }

    /**
     * @return The profileBannerUrl
     */
    //@SerializedName("profile_banner_url")
    public String getProfileBannerUrl() {
        return profileBannerUrl;
    }

    /**
     * @param profileBannerUrl The profile_banner_url
     */
    //@SerializedName("profile_banner_url")
    public void setProfileBannerUrl(String profileBannerUrl) {
        this.profileBannerUrl = profileBannerUrl;
    }

    //@JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    //@JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
