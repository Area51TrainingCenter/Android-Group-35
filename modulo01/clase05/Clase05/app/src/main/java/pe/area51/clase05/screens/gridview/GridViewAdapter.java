package pe.area51.clase05.screens.gridview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import pe.area51.clase05.R;
import pe.area51.clase05.helpers.frescolib.HelperImageClase03;
import pe.area51.clase05.models.PersonModel;

/**
 * Created by segundo on 24/08/17.
 */

public class GridViewAdapter extends ArrayAdapter<PersonModel> {

    Context context;

    public GridViewAdapter(Context context, ArrayList<PersonModel> people) {
        super(context, 0, people);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh;

        if (view == null) {

            vh = new ViewHolder();
            view = LayoutInflater.from(context)
                    .inflate(R.layout.row_person, parent, false);

            vh.photo = (SimpleDraweeView) view.findViewById(R.id.photo);
            vh.name = (TextView) view.findViewById(R.id.name);
            vh.time = (TextView) view.findViewById(R.id.time);

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        //Seteamos el nombre
        vh.name.setText(getItem(position).getName());
        //Seteamos el tiempo
        vh.time.setText(getItem(position).getTime() );


        //Mostramos la imagen
        HelperImageClase03 loader = new HelperImageClase03(context);
        loader.view = vh.photo;
        loader.url = getItem(position).getPhoto();
        loader.show();

        return view;
    }

    static class ViewHolder {
        SimpleDraweeView photo;
        TextView name, time;
    }
}
