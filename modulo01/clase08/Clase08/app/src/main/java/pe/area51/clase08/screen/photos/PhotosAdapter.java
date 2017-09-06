package pe.area51.clase08.screen.photos;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import pe.area51.clase08.R;
import pe.area51.clase08.model.Photos;

/**
 * Created by segundo on 5/09/17.
 */

public class PhotosAdapter extends ArrayAdapter<Photos> {
    Context context;

    public PhotosAdapter(@NonNull Context context, ArrayList<Photos> photos) {
        super(context, 0, photos);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {


        ViewHolder vh;
        if (view == null) {
            vh = new ViewHolder();

            view = LayoutInflater.from(context)
                    .inflate(R.layout.photo, parent, false);
            vh.photo = (ImageView) view.findViewById(R.id.photo);

            view.setTag(vh);

        } else {
            vh = (ViewHolder) view.getTag();
        }

        vh.photo.setImageResource(getItem(position).getImage());

        return view;
    }

    static class ViewHolder{
        ImageView photo;
    }

}
