package pe.area51.clase05.screens.launcher;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.area51.clase05.R;
import pe.area51.clase05.screens.home.MainActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        /*
        * Hilo para llamar al método
        * toHome() luego de 2 segundos
        */

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toHome();
            }
        }, 2000);


    }

    void toHome() {

        //Creamos la variable de cambio de pantallas(Activity)
        Intent intent = new Intent(
                LauncherActivity.this,
                MainActivity.class);

        //Lanzamos el cambio de pantallas
        startActivity(intent);

    }

}
