package pe.area51.clase03;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by segundo on 17/08/17.
 */

public class Clase03Application extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

    }


}
