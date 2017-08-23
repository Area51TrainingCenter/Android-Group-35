package pe.area51.clase03.screens.welcome.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import pe.area51.clase03.R;
import pe.area51.clase03.helpers.frescolib.HelperImageClase03;
import pe.area51.clase03.models.UserModel;

/**
 * Created by segundo on 22/08/17.
 */

public class UserAdapter extends ArrayAdapter<UserModel> {

    Context context;

    public UserAdapter(Context context, ArrayList<UserModel> users) {
        super(context, 0, users);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder vh;

        if (view == null) {
            //Asignamos el row_user.xml al view del elemento a mostrar
            view = LayoutInflater.from(context)
                    .inflate(R.layout.row_user, parent, false);
            //Inicializamos la instancia de la clase en memoria
            vh = new ViewHolder();
            //Vinculamos los componente con la clase ViewHolder
            vh.name = (TextView) view.findViewById(R.id.name);
            vh.description = (TextView) view.findViewById(R.id.description);
            vh.photo = (SimpleDraweeView) view.findViewById(R.id.photo);
            //Guardamos en memoria la vinculación a los datos
            //una sola vez en la propiedad tag() del view
            view.setTag(vh);

        } else {
            vh = (ViewHolder) view.getTag();
        }

        //enlazar datos al diseño(xml)
        vh.name.setText(getItem(position).getName());
        vh.description.setText(getItem(position).getDescription());

        //Para un ImageView
        //vh.photo.setImageResource(R.drawable.img_1);

        HelperImageClase03 loader = new HelperImageClase03(context);
        loader.url = getItem(position).getPhoto();
        loader.view = vh.photo;
        loader.show();





        return view;
    }

    static class ViewHolder {
        SimpleDraweeView photo;
        TextView name, description;
    }


}
