package pe.area51.clase05.screens.viewpager.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import pe.area51.clase05.models.PersonModel;
import pe.area51.clase05.screens.viewpager.fragments.PeopleFragment;

/**
 * Created by segundo on 29/08/17.
 */

public class PeopleStatePageAdapter extends FragmentStatePagerAdapter {

    ArrayList<PersonModel> people;

    public PeopleStatePageAdapter(FragmentManager fm, ArrayList<PersonModel> people) {
        super(fm);
        this.people = people;
    }

    @Override
    public Fragment getItem(int position) {

        PeopleFragment fragment = new PeopleFragment();
        Bundle bundle = new Bundle();

        //Traemos el objeto de la posición del arreglo people
        PersonModel pm = people.get(position);
        //Seteamos el objeto serializado
        bundle.putSerializable("people", pm);
        //Asignamos la variable bundle al fragmento
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return people.size();
    }
}
