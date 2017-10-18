package pe.area51.socialapp.screens.splash.view;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import pe.area51.socialapp.R;
import pe.area51.socialapp.helpers.analytics.SocialAppAnalytics;
import pe.area51.socialapp.helpers.session.SocialAppSession;
import pe.area51.socialapp.screens.feed.view.FeedActivity;

import pe.area51.socialapp.screens.login.view.LoginActivity;
import pe.area51.socialapp.widgets.activity.SocialAppActivity;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends SocialAppActivity
        implements EasyPermissions.PermissionCallbacks {

    private static final int STORAGE_PERM = 123;

    SocialAppSession session;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        context = this;

        //Inicializamos la variable de session
        session = new SocialAppSession(context);

        SocialAppAnalytics.trackingScreen(getApplication(), "splash", session);

        permissions();


        /*
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "pe.area51.socialapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        */



        /*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //toHome();
            }
        }, 2000);
        */

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


    @AfterPermissionGranted(STORAGE_PERM)
    public void permissions() {
        String[] perms = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        if (EasyPermissions.hasPermissions(this, perms)) {
            Log.d("SocialApp", " Paso");


            if (session.isLogin()) {
                Log.d("SocialApp", " Logueado");
                toHome();
            } else {
                Log.d("SocialApp", " No existe sesion");
                toLogin();
            }


        } else {
            Log.d("SocialApp", " No Paso");


            if (session.isLogin()) {
                Log.d("SocialApp", " Logueado");
                toHome();
            } else {
                Log.d("SocialApp", " No existe sesion");
                toLogin();
            }

            EasyPermissions.requestPermissions(this,
                    getString(R.string.permissions_storage),
                    STORAGE_PERM, perms);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.
                onRequestPermissionsResult(requestCode, permissions, grantResults, this);


    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
