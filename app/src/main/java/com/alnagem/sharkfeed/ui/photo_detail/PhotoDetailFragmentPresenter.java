package com.alnagem.sharkfeed.ui.photo_detail;

import android.util.Log;

import com.alnagem.sharkfeed.model.FlickrPhotoDetail;
import com.alnagem.sharkfeed.network.FlickrAPI;
import com.alnagem.sharkfeed.views.base.BaseMVPPresenter;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lalnagem on 2/28/18.
 */

public class PhotoDetailFragmentPresenter extends BaseMVPPresenter<PhotoDetailFragmentView> {

    String photoId;
    FlickrPhotoDetail photoDetail;

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

                    String id = jsonPhoto.getString("id");
                    String description = jsonPhoto.getString("description");
                    String imageUrl = "";

                    JSONObject jsonObjectPhotoUrls = jsonPhoto.getJSONObject("urls");
                    JSONArray jsonPhotoUrls = jsonObjectPhotoUrls.getJSONArray("url");

                    for (int i = 0; i < jsonPhotoUrls.length(); i++) {
                        imageUrl = jsonPhotoUrls.getJSONObject(i).getString("_content");
                        if (jsonPhotoUrls.getJSONObject(i).getString("type").equalsIgnoreCase("photopage")) {
                            break;
                        }
                    }

                    photoDetail = new FlickrPhotoDetail(id, description, imageUrl);
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


        FlickrAPI.photoInfo(photoId, successResponse, errorListener);
    }

    private void updateUI() {
        setViewImageUrl();
        setViewDescription();
    }

    private void setViewDescription() {
        getMvpView().setDescription(photoDetail.getDescription());
    }

    private void setViewImageUrl() {
        getMvpView().setImageUrl(photoDetail.getImageUrl());
    }

    public void setPhotoId(String id) {
        this.photoId = id;
        fetchPhotoDetails();
    }
}
