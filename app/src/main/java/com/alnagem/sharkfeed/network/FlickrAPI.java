package com.alnagem.sharkfeed.network;

import com.alnagem.sharkfeed.SharkFeedApplication;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by lalnagem on 2/28/18.
 */

public class FlickrAPI {

    public static final String FLICKR_API_URL = "https://api.flickr.com/services/rest/";
    public static final String FLICKR_API_KEY = "949e98778755d1982f537d56236bbb42";

    public static final String FLICKR_METHOD_PHOTO_SEARCH = "flickr.photos.search";
    public static final String FLICKR_METHOD_PHOTO_INFO = "flickr.photos.getInfo";


    public static void sharkSearch(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        // TODO add these as parameters in the request
        String searchUrl = FLICKR_API_URL + "?api_key=" + FLICKR_API_KEY + "&method=" + FLICKR_METHOD_PHOTO_SEARCH
                + "&text=shark&format=json&nojsoncallback=1&page=1&extras=url_t,url_c,url_l,url_o";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, searchUrl, null, responseListener, errorListener);
        SharkFeedApplication.getRequestQueue().add(request);
    }

    public static void photoInfo(String photoId, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        String infoUrl = FLICKR_API_URL + "?api_key=" + FLICKR_API_KEY + "&method=" + FLICKR_METHOD_PHOTO_INFO
                + "&format=json&nojsoncallback=1&photo_id=" + photoId;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, infoUrl, null, responseListener, errorListener);
        SharkFeedApplication.getRequestQueue().add(request);
    }

}
