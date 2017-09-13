package pe.area51.socialapp.screens.login.view;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import pe.area51.socialapp.R;
import pe.area51.socialapp.databinding.ActivityLoginBinding;
import pe.area51.socialapp.screens.login.viewmodel.LoginViewModel;
import pe.area51.socialapp.widgets.activity.SocialAppActivity;

public class LoginActivity extends SocialAppActivity {

    ActivityLoginBinding binding;
    LoginViewModel view;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        initBindind();

    }

    void initBindind() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        view = new LoginViewModel(context, binding);
        binding.setViewLogin(view);

        view.initView();

    }

}
