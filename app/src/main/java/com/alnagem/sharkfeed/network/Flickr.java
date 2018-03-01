package com.alnagem.sharkfeed.network;

import com.alnagem.sharkfeed.SharkFeedApplication;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lalnagem on 2/28/18.
 */

public class Flickr {

    public static final String FLICKR_API_URL = "https://api.flickr.com/services/rest/";
    public static final String FLICKR_API_KEY = "949e98778755d1982f537d56236bbb42";

    public static final String FLICKR_METHOD_PHOTO_SEARCH = "flickr.photos.search";

    public static void sharkSearch(Response.Listener<String> responseListener, Response.ErrorListener errorListener) {

        Map<String,String> params = new HashMap<String, String>();
        params.put("api_key", FLICKR_API_KEY);
        params.put("method", "flickr.photos.search");
        params.put("text", "shark");
        params.put("format" , "json");
//        params.put("nojsoncallback" , "1");
//        params.put("page", "1");
//        params.put("extras", "url_t,url_c,url_l,url_o");

        String searchUrl = FLICKR_API_URL + "?api_key=" + FLICKR_API_KEY + "&method=" + FLICKR_METHOD_PHOTO_SEARCH + "&text=shark&format=json&nojsoncallback=1&page=1&extras=url_t,url_c,url_l,url_o" ;

        JsonArrayRequestWithParams request = new JsonArrayRequestWithParams(Request.Method.GET, searchUrl, responseListener, errorListener, null);

        SharkFeedApplication.getRequestQueue().add(request);
    }

}
