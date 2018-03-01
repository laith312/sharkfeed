package com.alnagem.sharkfeed.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

/**
 * Created by lalnagem on 2/28/18.
 */

public class Util {

    private static final String TAG = "NetworkApi";

    public static void logToCurlRequest(Request<?> request) {

      /*
       * avoid all this string operations, if we're
       * not going to use it anyways.
       */

        final StringBuilder builder = new StringBuilder();
        builder.append("curl ");
        builder.append("-X \"");
        switch (request.getMethod()) {
            case Request.Method.POST:
                builder.append("POST");
                break;
            case Request.Method.GET:
                builder.append("GET");
                break;
            case Request.Method.PUT:
                builder.append("PUT");
                break;
            case Request.Method.DELETE:
                builder.append("DELETE");
                break;
        }

        builder.append("\"");

        try {
            if (request.getBody() != null) {
                builder.append(" -d ");
                String data = new String(request.getBody());
                data = data.replaceAll("\"", "\\\"");
                builder.append("\"");
                builder.append(data);
                builder.append("\"");
            }
            for (String key : request.getHeaders().keySet()) {
                builder.append(" -H '");
                builder.append(key);
                builder.append(": ");
                builder.append(request.getHeaders().get(key));
                builder.append("'");
            }
            builder.append(" \"");
            builder.append(request.getUrl());
            builder.append("\"");

            Log.i(TAG, builder.toString());
        } catch (AuthFailureError e) {
            Log.e(TAG, "Unable to get body of response or headers for curl logging");
        }
    }
}
