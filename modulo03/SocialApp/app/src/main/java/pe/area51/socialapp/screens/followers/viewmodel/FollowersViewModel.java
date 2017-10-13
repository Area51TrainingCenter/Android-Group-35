package pe.area51.socialapp.screens.followers.viewmodel;

import android.content.Context;

import java.util.ArrayList;

import pe.area51.socialapp.databinding.ActivityFollowersBinding;
import pe.area51.socialapp.models.app.FollowerModel;
import pe.area51.socialapp.screens.followers.view.FollowersAdapter;

/**
 * Created by segundo on 19/09/17.
 */

public class FollowersViewModel {

    ActivityFollowersBinding binding;
    Context context;

    FollowersAdapter adapter;

    public FollowersViewModel(ActivityFollowersBinding binding, Context context) {
        this.binding = binding;
        this.context = context;
    }

    public void initView() {
        getFollowers();
    }

    void getFollowers() {

        ArrayList<FollowerModel> list = new ArrayList<FollowerModel>();

        for (int i = 0; i < 100; i++) {
            FollowerModel fm = new FollowerModel();

            list.add(fm);
        }

        adapter = new FollowersAdapter(context, list);
        binding.list.setAdapter(adapter);

    }

}
