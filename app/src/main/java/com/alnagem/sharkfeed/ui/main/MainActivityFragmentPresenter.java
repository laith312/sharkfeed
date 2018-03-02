package com.alnagem.sharkfeed.ui.main;

import android.util.Log;

import com.alnagem.sharkfeed.model.FlickrPhoto;
import com.alnagem.sharkfeed.network.FlickrAPI;
import com.alnagem.sharkfeed.views.base.BaseMVPPresenter;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalnagem on 2/28/18.
 */

public class MainActivityFragmentPresenter extends BaseMVPPresenter<MainActivityFragmentView> {

    private List<FlickrPhoto> searchResults = new ArrayList<>();

    @Override
    protected void onMvpViewAttached() {
        super.onMvpViewAttached();

        fetchSharksFromFlickr();
    }

    void fetchSharksFromFlickr() {
        Response.Listener<JSONObject> successResponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject photos = response.getJSONObject("photos");
                    JSONArray photosAry = photos.getJSONArray("photo");

                    for (int i = 0; i < photosAry.length(); i++) {
                        JSONObject currentPhoto = photosAry.getJSONObject(i);

                        if (currentPhoto.has("url_t")) {
                            searchResults.add(new FlickrPhoto(currentPhoto.getString("id"), currentPhoto.getString("url_t")));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                updateUI();
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MainFragmentPresenter", "Network Error:" + error.getLocalizedMessage());
            }
        };

        FlickrAPI.sharkSearch(successResponse, errorListener);
    }

    public void updateUI() {
        getMvpView().updateSearchResults(searchResults);
        getMvpView().stopRefresh();
    }

}
