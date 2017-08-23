package pe.area51.clase03.screens.welcome.view;

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

import pe.area51.clase03.R;
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
            view = LayoutInflater.from(context)
                    .inflate(R.layout.row_user, parent, false);

            vh = new ViewHolder();

        } else {

        }

        return view;
    }

    static class ViewHolder {
        SimpleDraweeView photo;
        TextView name, description;
    }


}
