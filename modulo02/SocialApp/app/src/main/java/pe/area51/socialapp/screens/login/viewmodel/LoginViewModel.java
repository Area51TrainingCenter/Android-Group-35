package pe.area51.socialapp.screens.login.viewmodel;

import android.content.Context;

import pe.area51.socialapp.databinding.ActivityLoginBinding;

/**
 * Created by segundo on 12/09/17.
 */

public class LoginViewModel {

    Context context;
    ActivityLoginBinding binding;


    public LoginViewModel(Context context, ActivityLoginBinding binding) {

        this.binding = binding;
        this.context = context;

    }

    public void initView(){

    }


}
