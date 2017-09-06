package pe.area51.clase08.screen.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import pe.area51.clase08.Clase08Globals;
import pe.area51.clase08.R;
import pe.area51.clase08.model.Pokemon;
import pe.area51.clase08.screen.form.FormActivity;

public class MainActivity extends AppCompatActivity {

    Context context;
    PokemonsAdapter adapter;
    ListView pokemons;
    Button btnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        Clase08Globals.pokemons = new ArrayList<Pokemon>();

        pokemons = (ListView) findViewById(R.id.pokemons);
        btnNew = (Button) findViewById(R.id.btnNew);


        adapter = new PokemonsAdapter(context, Clase08Globals.pokemons);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("","llega tamaño: " + Clase08Globals.pokemons.size());
        adapter.notifyDataSetChanged();

    }
}
