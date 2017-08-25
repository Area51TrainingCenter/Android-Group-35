package pe.area51.clase05.screens.listview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import pe.area51.clase05.R;
import pe.area51.clase05.models.PersonModel;
import pe.area51.clase05.screens.gridview.GridViewAdapter;

public class ListViewActivity extends AppCompatActivity {



    GridViewAdapter adapter;
    ListView people;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        context = this;

        people = (ListView) findViewById(R.id.people);


        ArrayList<PersonModel> arreglo = new ArrayList<PersonModel>();
        for (int i = 1; i < 30; i++) {
            PersonModel pm = new PersonModel();
            pm.setPhoto("http://segundoacosta.com/people/img_" + i + ".jpg");

            arreglo.add(pm);
        }


        adapter = new GridViewAdapter(context, arreglo);
        people.setAdapter(adapter);


    }



}
