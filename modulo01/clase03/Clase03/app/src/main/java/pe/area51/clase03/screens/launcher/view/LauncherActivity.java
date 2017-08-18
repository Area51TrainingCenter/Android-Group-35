package pe.area51.clase03.screens.launcher.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.area51.clase03.R;
import pe.area51.clase03.screens.welcome.view.WelcomeActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toWelcome();
            }
        }, 2000);

    }

    public void toWelcome() {

        Intent intent = new Intent(LauncherActivity.this, WelcomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        //finish();


    }

}
