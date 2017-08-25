package pe.area51.clase05.screens.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import pe.area51.clase05.R;

public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Clase05","onDestroy");
    }
}
