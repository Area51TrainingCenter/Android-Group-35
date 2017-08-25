package pe.area51.clase05;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by segundo on 24/08/17.
 */

public class Clase05Application extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Inicializamos Frescolib
        Fresco.initialize(this);

    }
}
