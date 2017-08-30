package pe.area51.clase05.screens.listview;

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

import java.util.ArrayList;

import pe.area51.clase05.R;
import pe.area51.clase05.helpers.frescolib.HelperImageClase03;
import pe.area51.clase05.models.PersonModel;

/**
 * Created by segundo on 29/08/17.
 */

public class ListviewAdapter extends ArrayAdapter<PersonModel> {

    Context context;

    public ListviewAdapter(Context context, ArrayList<PersonModel> people) {
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
                    .inflate(R.layout.row_person_listview, parent, false);

            vh.name = (TextView) view.findViewById(R.id.name);
            vh.message = (TextView) view.findViewById(R.id.message);
            vh.message_count = (TextView) view.findViewById(R.id.message_count);
            vh.time = (TextView) view.findViewById(R.id.time);
            vh.photo = (SimpleDraweeView) view.findViewById(R.id.photo);

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        PersonModel pm = getItem(position);

        vh.name.setText(pm.getName());
        vh.message.setText(pm.getMessage());
        vh.message_count.setText("" + pm.getMessage_count());
        vh.time.setText(pm.getTime());

        HelperImageClase03 loader = new HelperImageClase03(context);
        loader.url = pm.getPhoto();
        loader.view = vh.photo;
        loader.show();


        return view;
    }

    static class ViewHolder {
        TextView name, message, message_count, time;
        SimpleDraweeView photo;
    }

}
