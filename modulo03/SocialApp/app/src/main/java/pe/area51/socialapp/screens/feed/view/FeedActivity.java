package pe.area51.socialapp.screens.feed.view;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import io.realm.Realm;
import pe.area51.socialapp.R;

import pe.area51.socialapp.databinding.ActivityFeedBinding;
import pe.area51.socialapp.screens.feed.viewmodel.FeedViewModel;
import pe.area51.socialapp.widgets.activity.SocialAppActivity;

public class FeedActivity extends SocialAppActivity {

    ActivityFeedBinding binding;
    FeedViewModel view;
    Context context;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        realm = Realm.getDefaultInstance();
        initBinding();

        initToolbar();
        setTitle(getResources().getString(R.string.feed_title));

    }

    void initBinding() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);
        view = new FeedViewModel(context, binding, realm);
        view.initView();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (realm != null) {
            realm.close();
        }

    }
}
