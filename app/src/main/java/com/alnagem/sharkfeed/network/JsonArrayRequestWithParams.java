package com.alnagem.sharkfeed.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by lalnagem on 2/28/18.
 */

public class JsonArrayRequestWithParams extends StringRequest {
    private Map<String, String> mParams;

    public JsonArrayRequestWithParams(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> params) {
        super(method, url, listener, errorListener);
        mParams = params;
        Util.logToCurlRequest(this);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}
