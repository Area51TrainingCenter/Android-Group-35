package pe.area51.socialapp.screens.login.view;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

import pe.area51.socialapp.R;
import pe.area51.socialapp.databinding.ActivityLoginBinding;
import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.screens.login.viewmodel.LoginViewModel;
import pe.area51.socialapp.widgets.activity.SocialAppActivity;

public class LoginActivity extends SocialAppActivity
        implements LoginFragment.onFacebook {

    ActivityLoginBinding binding;
    LoginViewModel view;
    Context context;

    //Facebook
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        initBindind();

    }

    void initBindind() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        view = new LoginViewModel(context, binding, getSupportFragmentManager());
        binding.setViewLogin(view);


        view.initView();

    }


    public void loginFacebook() {

        callbackManager = CallbackManager.Factory.create();

        //Seteamos los permisos de Facebook
        LoginManager.getInstance().
                logInWithReadPermissions(this,
                        Arrays.asList("email", "public_profile", "user_friends"));

        LoginManager.getInstance().registerCallback(
                callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        SocialAppLog.getMessage("object: " + object);
                                        SocialAppLog.getMessage("response: " + response);
                                    }
                                }
                        );


                        Bundle parameters = new Bundle();
                        parameters.
                                putString("fields", "id,email,name,first_name,last_name,link,gender,picture");
                        request.setParameters(parameters);

                        request.executeAsync();


                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                }
        );


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Recibimos el resultado de facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public String llamame() {
        return "";
    }

    @Override
    public void initFacebook() {
        loginFacebook();
    }


}
