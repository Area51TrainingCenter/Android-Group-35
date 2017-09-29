package pe.area51.socialapp.screens.login.view;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import pe.area51.socialapp.R;
import pe.area51.socialapp.SocialAppApplication;
import pe.area51.socialapp.SocialAppGlobals;
import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.helpers.session.SocialAppSession;
import pe.area51.socialapp.screens.feed.view.FeedActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    View view;
    EditText txtpassword, txtemail;
    Button btnlogin;
    LinearLayout loaders;

    SocialAppSession session;
    Context context;
    Activity activity;

    int error_code = 0;

    public LoginFragment() {


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();
        activity = getActivity();
        session = new SocialAppSession(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);

        txtemail = (EditText) view.findViewById(R.id.txtemail);
        txtpassword = (EditText) view.findViewById(R.id.txtpassword);
        btnlogin = (Button) view.findViewById(R.id.btnlogin);
        loaders = (LinearLayout) view.findViewById(R.id.loaders);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {

                    toLogin();

                } else {
                    //Mostramos mensaje de error
                    String message = "";
                    switch (error_code) {
                        case 3: {
                            message = "Ingrese un email válido";
                            break;
                        }
                        case 4: {
                            message = "La contraseña es inválida, debe tener más de 6 caracteres";
                            break;
                        }
                    }

                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT)
                            .show();

                }


            }
        });


    }


    public void toLogin() {

        //Mostramos el loader al usuario
        loaders.setVisibility(View.VISIBLE);


        //Campos validos, se procede al registro
        SocialAppLog.getMessage("Login válido");
        JSONObject parameters = new JSONObject();

        try {

            parameters.put(SocialAppGlobals.api_par_email,
                    txtemail.getText().toString());
            parameters.put(SocialAppGlobals.api_par_password,
                    txtpassword.getText().toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        SocialAppLog.getMessage("url: " + SocialAppGlobals.api_module_user_login);
        SocialAppLog.getMessage("parameters: " + parameters);

        JsonObjectRequest jor =
                new JsonObjectRequest(
                        Request.Method.POST,
                        SocialAppGlobals.api_module_user_login,
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
                                                if (data.has(SocialAppGlobals.api_res_name)) {
                                                    session.setName(data.getString(SocialAppGlobals.api_res_name));
                                                }
                                                if (data.has(SocialAppGlobals.api_res_lastname)) {
                                                    session.setLastname(data.getString(SocialAppGlobals.api_res_lastname));
                                                }
                                                if (data.has(SocialAppGlobals.api_res_email)) {
                                                    session.setEmail(data.getString(SocialAppGlobals.api_res_email));
                                                }
                                                if (data.has(SocialAppGlobals.api_res_users_id)) {
                                                    session.setId(data.getString(SocialAppGlobals.api_res_users_id));
                                                }

                                                session.saveSession();


                                                loaders.setVisibility(View.GONE);

                                                Intent intent = new Intent(activity, FeedActivity.class);
                                                activity.startActivity(intent);

                                                activity.finish();

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

                                loaders.setVisibility(View.GONE);
                                Toast.makeText(context, "Error de servidor", Toast.LENGTH_SHORT)
                                        .show();

                            }
                        }
                );

        SocialAppApplication.getInstance().addToRequestQueue(jor);


    }


    public boolean validate() {

        boolean state = false;

        if (!txtpassword.getText().toString().equals("")
                && txtpassword.getText().toString().length() > 6) {

            if (error_code == 0) {
                state = true;
                error_code = 0;
            }
        } else {
            error_code = 4;
        }

        if (!txtemail.getText().toString().equals("")
                && validateEmail(txtemail.getText().toString())) {
            if (error_code == 0) {
                error_code = 0;
                state = true;
            }
        } else {
            error_code = 3;
        }


        return state;
    }

    public boolean validateEmail(String email) {
        return !TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
