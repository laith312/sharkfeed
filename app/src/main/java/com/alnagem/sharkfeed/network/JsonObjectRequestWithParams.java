package com.alnagem.sharkfeed.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lalnagem on 2/28/18.
 */

public class JsonObjectRequestWithParams extends JsonObjectRequest {

    private Map<String, String> mParams;

    public JsonObjectRequestWithParams(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Map<String, String> params) {

        super(method, url, jsonRequest, listener, errorListener);
        mParams = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        return params;
    }
}
