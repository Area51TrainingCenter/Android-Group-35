package pe.area51.clase08.screen.photos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Random;

import pe.area51.clase08.Clase08Globals;
import pe.area51.clase08.R;
import pe.area51.clase08.model.Photos;

public class PhotosActivity extends AppCompatActivity {

    Context context;
    PhotosAdapter adapter;
    GridView photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        context = this;
        photos = (GridView) findViewById(R.id.photos);

        final ArrayList<Photos> arreglo = new ArrayList<Photos>();

        int images[] = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
                R.drawable.a4, R.drawable.a5};

        for (int i = 0; i < 100; i++) {
            Photos photo = new Photos();
            Random random = new Random();
            photo.setImage( images[random.nextInt(5)] );
            arreglo.add(photo);
        }

        adapter = new PhotosAdapter(context, arreglo);
        photos.setAdapter(adapter);

        photos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Clase08Globals.imagen = arreglo.get(i).getImage();
                finish();
            }
        });

    }
}
