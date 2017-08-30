package pe.area51.clase05.screens.viewpager.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import pe.area51.clase05.R;
import pe.area51.clase05.models.PersonModel;

public class ViewPagerActivity extends AppCompatActivity {

    Context context;
    PeopleStatePageAdapter adapter;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        pager = (ViewPager) findViewById(R.id.pager);

        //Llenamos la data del arreglo
        ArrayList<PersonModel> arreglo = new ArrayList<PersonModel>();

        for (int i = 1; i < 30; i++) {
            PersonModel pm = new PersonModel();
            Random random = new Random();

            //Obtenemos el tiempo actual
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            pm.setName("Nombre " + i);
            pm.setMessage("Mensaje de " + i);
            pm.setTime("" + sdf.format(cal.getTime())); //Momento actual
            pm.setPhoto("http://segundoacosta.com/people/img_" + i + ".jpg");
            pm.setMessage_count(random.nextInt(20)); //Cantidad aleatoria

            arreglo.add(pm);
        }

        //Inicializamos el adapter
        adapter = new PeopleStatePageAdapter(getSupportFragmentManager(), arreglo);
        pager.setAdapter(adapter);

    }
}
