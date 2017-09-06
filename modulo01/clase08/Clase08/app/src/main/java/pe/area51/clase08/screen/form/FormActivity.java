package pe.area51.clase08.screen.form;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import pe.area51.clase08.Clase08Globals;
import pe.area51.clase08.R;
import pe.area51.clase08.model.Pokemon;
import pe.area51.clase08.screen.photos.PhotosActivity;

public class FormActivity extends AppCompatActivity {

    Context context;

    EditText txtname, txttype;
    ImageView photo;
    TextView btnPhoto;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        context = this;

        txtname = (EditText) findViewById(R.id.txtname);
        txttype = (EditText) findViewById(R.id.txttype);
        photo = (ImageView) findViewById(R.id.photo);

        btnPhoto = (TextView) findViewById(R.id.btnPhoto);
        btnSave = (Button) findViewById(R.id.btnSave);


        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FormActivity.this, PhotosActivity.class);
                startActivity(intent);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Clase08Globals.imagen != 0
                        && !txtname.getText().toString().equals("")
                        && !txttype.getText().toString().equals("")) {
                    //Guarda
                    Pokemon pokemon = new Pokemon();
                    pokemon.setName(txtname.getText().toString());
                    pokemon.setType(txttype.getText().toString());
                    pokemon.setImage(Clase08Globals.imagen);

                    Clase08Globals.pokemons.add(pokemon);

                    Clase08Globals.imagen = 0;
                    finish(); //Regresamos a la anterior pantalla

                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Clase08Globals.imagen != 0) {
            photo.setImageResource(Clase08Globals.imagen);
        }

    }
}
