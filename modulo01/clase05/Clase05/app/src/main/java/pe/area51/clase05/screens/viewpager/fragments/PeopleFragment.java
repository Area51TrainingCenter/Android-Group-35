package pe.area51.clase05.screens.viewpager.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.area51.clase05.R;
import pe.area51.clase05.helpers.log.HelperLog;
import pe.area51.clase05.models.PersonModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    Context context;

    PersonModel pm;

    View view;
    TextView name;

    public PeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity(); //Contexto que hereda el fragmento

        pm = (PersonModel) getArguments().getSerializable("people");
        HelperLog.getMessage("PersonModel: " + pm.getPhoto());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_people, container, false);
        name = (TextView) view.findViewById(R.id.name);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Aquí va la logica
        name.setText(pm.getName());


    }
}
