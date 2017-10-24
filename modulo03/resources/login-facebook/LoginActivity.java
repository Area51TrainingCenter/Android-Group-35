package pe.area51.socialapp.screens.login.view;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import pe.area51.socialapp.R;
import pe.area51.socialapp.SocialAppApplication;
import pe.area51.socialapp.SocialAppGlobals;
import pe.area51.socialapp.databinding.ActivityLoginBinding;
import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.helpers.session.SocialAppSession;
import pe.area51.socialapp.screens.feed.view.FeedActivity;
import pe.area51.socialapp.screens.login.viewmodel.LoginViewModel;
import pe.area51.socialapp.widgets.activity.SocialAppActivity;

public class LoginActivity extends SocialAppActivity
        implements LoginFragment.onFacebook,
        SignUpFragment.onFacebook {

    ActivityLoginBinding binding;
    LoginViewModel view;
    Context context;

    SocialAppSession session;

    //Facebook
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        session = new SocialAppSession(context);

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
                    public void onSuccess(final LoginResult loginResult) {

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject user, GraphResponse response) {
                                        SocialAppLog.getMessage("user: " + user);
                                        SocialAppLog.getMessage("response: " + response);
                                        SocialAppLog.getMessage("accestoken: " + AccessToken.getCurrentAccessToken().getToken());

                                        //Set properties for session
                                        session.setFacebook_token(AccessToken.getCurrentAccessToken().getToken());

                                        try {

                                            if (user.has("first_name")) {
                                                session.setName(user.getString("first_name"));
                                            }

                                            if (user.has("last_name")) {
                                                session.setLastname(user.getString("last_name"));
                                            }

                                            if (user.has("email")) {
                                                session.setEmail(user.getString("email"));
                                            }

                                            if (user.has("id")) {
                                                session.setFacebook_id(user.getString("id"));
                                            }

                                            toRegisterFacebook();


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

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


    public void toRegisterFacebook() {

        //Mostramos el loader al usuario
        //binding.loaders.setVisibility(View.VISIBLE);


        //Campos validos, se procede al registro
        SocialAppLog.getMessage("Registro válido");
        JSONObject parameters = new JSONObject();

        try {

            parameters.put(SocialAppGlobals.api_par_name,
                    session.getName());

            parameters.put(SocialAppGlobals.api_par_lastname,
                    session.getLastname());

            parameters.put(SocialAppGlobals.api_par_email,
                    session.getEmail());

            //Nuevos parametros
            parameters.put(SocialAppGlobals.api_par_facebook_token,
                    session.getFacebook_token());

            parameters.put(SocialAppGlobals.api_par_facebook_id,
                    session.getFacebook_id());

            parameters.put(SocialAppGlobals.api_par_fcm_token,
                    session.getFirebase_token());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        SocialAppLog.getMessage("url: " + SocialAppGlobals.api_module_user_facebook);
        SocialAppLog.getMessage("parameters: " + parameters);

        JsonObjectRequest jor =
                new JsonObjectRequest(
                        Request.Method.POST,
                        SocialAppGlobals.api_module_user_facebook,
                        parameters,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                SocialAppLog.getMessage("response: " + response);
                                //Primero validamos el state

                                try {

                                    if (response.has(SocialAppGlobals.api_res_state)) {
                                        if (response.getString(SocialAppGlobals.api_res_state)
                                                .equals(SocialAppGlobals.api_res_state_ok)) {

                                            if (response
                                                    .has(SocialAppGlobals.api_res_data)) {

                                                JSONObject data =
                                                        response.getJSONObject(SocialAppGlobals.api_res_data);

                                                //Guardamos la sesión
                                                if (data.has(SocialAppGlobals.api_res_users_id)) {
                                                    session.setId(data.getString(SocialAppGlobals.api_res_users_id));
                                                }

                                                session.saveSession();


                                                Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
                                                startActivity(intent);
                                                finish();

                                            }

                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                SocialAppLog.getMessage("error: " + error.getMessage());

                                Toast.makeText(context, "Error de servidor", Toast.LENGTH_SHORT)
                                        .show();

                            }
                        }
                );

        SocialAppApplication.getInstance().addToRequestQueue(jor);


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


    @Override
    public void initFacebookR() {
        loginFacebook();
    }
}
