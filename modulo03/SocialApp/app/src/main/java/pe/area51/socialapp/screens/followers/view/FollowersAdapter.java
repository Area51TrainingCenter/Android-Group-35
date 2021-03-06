package pe.area51.socialapp.screens.followers.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import pe.area51.socialapp.R;
import pe.area51.socialapp.databinding.ItemFollowersBinding;
import pe.area51.socialapp.models.app.FollowerModel;

/**
 * Created by segundo on 19/09/17.
 */

public class FollowersAdapter extends ArrayAdapter<FollowerModel> {

    Context context;

    public FollowersAdapter(@NonNull Context context, ArrayList<FollowerModel> list) {
        super(context, 0, list);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        ItemFollowersBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.item_followers, parent, false);

        binding.following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return binding.getRoot();
    }
}
