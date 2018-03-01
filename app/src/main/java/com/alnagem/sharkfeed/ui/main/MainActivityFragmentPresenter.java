package com.alnagem.sharkfeed.ui.main;

import android.util.Log;

import com.alnagem.sharkfeed.model.FlickrPhoto;
import com.alnagem.sharkfeed.network.Flickr;
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

    List<FlickrPhoto> searchResults = new ArrayList<>();

    @Override
    protected void onMvpViewAttached() {
        super.onMvpViewAttached();

        fetchSharksFromFlickr();
    }

    public void fetchSharksFromFlickr() {

        Log.e("zzzz", "fetchSharksFromFlickr");

        Response.Listener<String> successResponse = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonResponse = null;
                JSONObject photos = null;
                JSONArray photosAry = null;
                try {
                    jsonResponse = new JSONObject(response);
                    photos = jsonResponse.getJSONObject("photos");
                    photosAry = photos.getJSONArray("photo");

                    for (int i = 0; i < photosAry.length(); i++) {
                        JSONObject currentPhoto = photosAry.getJSONObject(i);

                        if (currentPhoto.has("url_t")) {
                            searchResults.add(new FlickrPhoto(currentPhoto.getString("id"), currentPhoto.getString("url_t")));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("zzz", "JSON ERROR!!!!");
                }

                updateUI();
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("zzz", error.getLocalizedMessage());
            }
        };

        Flickr.sharkSearch(successResponse, errorListener);
    }

    public void updateUI() {
        Log.e("zzz", "updateUI");
        getMvpView().updateSearchResults(searchResults);
        getMvpView().stopRefresh();
    }
}
