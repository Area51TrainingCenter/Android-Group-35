package pe.area51.socialapp.screens.followers.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.area51.socialapp.R;
import pe.area51.socialapp.databinding.ActivityFollowersBinding;
import pe.area51.socialapp.screens.followers.viewmodel.FollowersViewModel;
import pe.area51.socialapp.widgets.activity.SocialAppActivity;

public class FollowersActivity extends SocialAppActivity {

    ActivityFollowersBinding binding;
    FollowersViewModel view;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        initBinding();

        initToolbar();
        setTitle(getResources().getString(R.string.followers_title));
        
    }

    void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_followers);
        view = new FollowersViewModel(binding, context);

        binding.setViewFollowers(view);
        view.initView();


    }
}
