package pe.area51.socialapp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by segundo on 7/09/17.
 */

public class SocialAppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Inicializamos FrescoLib
        Fresco.initialize(this);

    }
}
