package pe.area51.clase02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


//import pe.area51.clase02.util.Log;


public class MainActivity extends AppCompatActivity {

    String tag = "Clase02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(tag,"onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag,"onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag,"onDestroy");
    }
}
