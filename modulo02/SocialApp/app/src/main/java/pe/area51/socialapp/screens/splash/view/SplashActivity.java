package pe.area51.socialapp.screens.splash.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import pe.area51.socialapp.R;
import pe.area51.socialapp.screens.feed.view.FeedActivity;
import pe.area51.socialapp.screens.login.view.LoginActivity;
import pe.area51.socialapp.widgets.activity.SocialAppActivity;

public class SplashActivity extends SocialAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toLogin();
            }
        }, 2000);

    }

    void toHome() {
        Intent intent = new Intent(SplashActivity.this,
                FeedActivity.class);
        startActivity(intent);
    }

    void toLogin() {
        Intent intent = new Intent(SplashActivity.this,
                LoginActivity.class);
        startActivity(intent);
    }


}
