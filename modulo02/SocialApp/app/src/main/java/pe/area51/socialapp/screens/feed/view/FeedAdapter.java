package pe.area51.socialapp.screens.feed.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import pe.area51.socialapp.databinding.ItemFeedBinding;
import pe.area51.socialapp.models.FeedModel;

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

        //ItemFeedBinding binding

        return view;
    }
}
