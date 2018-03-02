package com.alnagem.sharkfeed.model;

import java.io.Serializable;

/**
 * Created by lalnagem on 2/28/18.
 */

public class FlickrPhoto implements Serializable {
    String id;
    String thumbnailUrl;
    String fullUrl;
    String description;

    public FlickrPhoto(String id, String thumbnailUrl) {
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
    }

    public FlickrPhoto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
