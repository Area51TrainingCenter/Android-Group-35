package pe.area51.clase05.screens.listview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import pe.area51.clase05.R;
import pe.area51.clase05.helpers.data.PeopleHelper;
import pe.area51.clase05.models.PersonModel;
import pe.area51.clase05.screens.gridview.GridViewAdapter;

public class ListViewActivity extends AppCompatActivity {

    ListView people;
    Context context;

    ListviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        context = this;

        people = (ListView) findViewById(R.id.people);


        ArrayList<PersonModel> arreglo = PeopleHelper.getPeople();

        adapter = new ListviewAdapter(context, arreglo);
        people.setAdapter(adapter);

    }


}
