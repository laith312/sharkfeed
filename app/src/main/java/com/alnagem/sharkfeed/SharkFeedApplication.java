package com.alnagem.sharkfeed;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by lalnagem on 2/28/18.
 */

public class SharkFeedApplication extends Application {
    private static RequestQueue sRequestQueue;
    private static SharkFeedApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sRequestQueue = Volley.newRequestQueue(this);
        sInstance = this;
    }

    public static RequestQueue getRequestQueue() {
        return sRequestQueue;
    }
}
