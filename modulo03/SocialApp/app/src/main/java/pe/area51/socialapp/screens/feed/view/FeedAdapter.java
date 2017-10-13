package pe.area51.socialapp.screens.feed.view;

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
import pe.area51.socialapp.databinding.ItemFeedBinding;
import pe.area51.socialapp.helpers.frescolib.SocialAppImage;
import pe.area51.socialapp.models.app.FeedModel;

/**
 * Created by segundo on 14/09/17.
 */

public class FeedAdapter extends ArrayAdapter<FeedModel> {


    Context context;

    public FeedAdapter(@NonNull Context context, ArrayList<FeedModel> feed) {
        super(context, 0, feed);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        ItemFeedBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.item_feed, parent, false);

        FeedModel feed = getItem(position);


        binding.title.setText(feed.getTitle());
        binding.comments.setText("" + feed.getComments());
        binding.favourites.setText("" + feed.getFavourites());

        //Para la imagen
        SocialAppImage loader = new SocialAppImage(context);
        loader.url = feed.getPhoto();
        loader.view = binding.photo;
        loader.show();


        return binding.getRoot();
    }
}
