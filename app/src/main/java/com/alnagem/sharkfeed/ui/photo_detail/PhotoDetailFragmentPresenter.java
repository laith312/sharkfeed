package com.alnagem.sharkfeed.ui.photo_detail;

import android.util.Log;

import com.alnagem.sharkfeed.model.FlickrPhoto;
import com.alnagem.sharkfeed.network.FlickrAPI;
import com.alnagem.sharkfeed.views.base.BaseMVPPresenter;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lalnagem on 2/28/18.
 */

public class PhotoDetailFragmentPresenter extends BaseMVPPresenter<PhotoDetailFragmentView> {

    FlickrPhoto photoDetail;

    @Override
    protected void onMvpViewAttached() {
        super.onMvpViewAttached();
    }

    private void fetchPhotoDetails() {

        // TODO Probably better to use retrofit to map object creation
        Response.Listener<JSONObject> successResponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonPhoto = response.getJSONObject("photo");
                    String description = jsonPhoto.getJSONObject("description").getString("_content");
                    photoDetail.setDescription(description);
                    updateUI();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MainFragmentPresenter", "Network Error:" + error.getLocalizedMessage());
            }
        };


        FlickrAPI.photoInfo(photoDetail.getId(), successResponse, errorListener);
    }

    private void updateUI() {
        setViewImageUrl();
        setViewDescription();
    }

    private void setViewDescription() {
        getMvpView().setDescription(photoDetail.getDescription());
    }

    private void setViewImageUrl() {
        if (getMvpView() != null && photoDetail != null) {
            getMvpView().setImageUrl(photoDetail.getFullUrl());
        }
    }

    void setPhoto(FlickrPhoto photo) {
        this.photoDetail = photo;
        updateUI();
        fetchPhotoDetails();
    }
}
