package pe.area51.socialapp;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by segundo on 7/09/17.
 */

public class SocialAppApplication extends Application {

    private static SocialAppApplication volleyInstance;
    private RequestQueue mRequestQueue;

    public static final String TAG = SocialAppApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        //Inicializamos FrescoLib
        Fresco.initialize(this);

        //Volley
        volleyInstance = this;


    }



    //============================================================================================================================================================
    // VOLLEY
    //============================================================================================================================================================


    public static synchronized SocialAppApplication getInstance() {
        return volleyInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}
