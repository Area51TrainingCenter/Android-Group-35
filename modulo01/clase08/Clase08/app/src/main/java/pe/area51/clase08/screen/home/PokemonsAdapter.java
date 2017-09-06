package pe.area51.clase08.screen.home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pe.area51.clase08.R;
import pe.area51.clase08.model.Pokemon;

/**
 * Created by segundo on 5/09/17.
 */

public class PokemonsAdapter extends ArrayAdapter<Pokemon> {

    Context context;

    public PokemonsAdapter(@NonNull Context context, ArrayList<Pokemon> pokemons) {
        super(context, 0, pokemons);

        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        Viewholder vh;
        if (view == null) {
            vh = new Viewholder();

            view = LayoutInflater.from(context)
                    .inflate(R.layout.row_pokemon, parent, false);
            vh.photo = (ImageView) view.findViewById(R.id.photo);
            vh.name = (TextView) view.findViewById(R.id.name);
            vh.type = (TextView) view.findViewById(R.id.type);

            view.setTag(vh);

        } else {
            vh = (Viewholder) view.getTag();
        }

        vh.name.setText(getItem(position).getName());
        vh.type.setText(getItem(position).getType());

        vh.photo.setImageResource(getItem(position).getImage());

        return view;
    }

    static class Viewholder {
        TextView name, type;
        ImageView photo;
    }


}
