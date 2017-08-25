package pe.area51.clase05.screens.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pe.area51.clase05.R;
import pe.area51.clase05.screens.gridview.GridViewActivity;
import pe.area51.clase05.screens.listview.ListViewActivity;
import pe.area51.clase05.screens.viewpager.ViewPagerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_gridview = (Button) findViewById(R.id.btn_gridview);
        Button btn_listview = (Button) findViewById(R.id.btn_listview);
        Button btn_viewpager = (Button) findViewById(R.id.btn_viewpager);


        btn_gridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,GridViewActivity.class);
                startActivity(intent);

            }
        });

        btn_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intent);

            }
        });

        btn_viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);

            }
        });

    }

}
