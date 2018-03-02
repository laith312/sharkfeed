package com.alnagem.sharkfeed.model;

/**
 * Created by lalnagem on 3/1/18.
 */

public class FlickrPhotoDetail {
    String id;
    String description;
    String imageUrl;

    public FlickrPhotoDetail(String id, String description, String imageUrl) {
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
