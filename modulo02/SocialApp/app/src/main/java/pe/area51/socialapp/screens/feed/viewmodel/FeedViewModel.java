package pe.area51.socialapp.screens.feed.viewmodel;

import android.content.Context;

import java.util.ArrayList;

import pe.area51.socialapp.databinding.ActivityFeedBinding;
import pe.area51.socialapp.models.FeedModel;
import pe.area51.socialapp.screens.feed.view.FeedAdapter;

/**
 * Created by segundo on 14/09/17.
 */

public class FeedViewModel {

    Context context;
    ActivityFeedBinding binding;

    FeedAdapter adapter;

    public FeedViewModel(Context context, ActivityFeedBinding binding) {
        this.context = context;
        this.binding = binding;
    }

    public void initView() {

        ArrayList<FeedModel> feed = new ArrayList<FeedModel>();
        for (int i = 0; i < 100; i++) {
            FeedModel model = new FeedModel();
            feed.add(model);
        }

        adapter = new FeedAdapter(context, feed);
        binding.list.setAdapter(adapter);

    }

}
