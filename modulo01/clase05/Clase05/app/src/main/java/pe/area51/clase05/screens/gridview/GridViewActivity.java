package pe.area51.clase05.screens.gridview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;

import pe.area51.clase05.R;
import pe.area51.clase05.helpers.data.PeopleHelper;
import pe.area51.clase05.models.PersonModel;

public class GridViewActivity extends AppCompatActivity {

    GridViewAdapter adapter;
    GridView people;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        context = this;

        people = (GridView) findViewById(R.id.people);

        /*
        PeopleHelper ph = new PeopleHelper();
        ph.getItem();
        */

        ArrayList<PersonModel> arreglo = PeopleHelper.getPeople();

        adapter = new GridViewAdapter(context, arreglo);
        people.setAdapter(adapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Clase05", "onDestroy");
    }

}
