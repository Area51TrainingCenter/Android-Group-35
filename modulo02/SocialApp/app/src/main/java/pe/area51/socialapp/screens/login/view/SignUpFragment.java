package pe.area51.socialapp.screens.login.view;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import pe.area51.socialapp.databinding.FragmentSignUpBinding;
import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.helpers.session.SocialAppSession;
import pe.area51.socialapp.screens.feed.view.FeedActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    FragmentSignUpBinding binding;
    SocialAppSession sesion;
    Context context;
    Activity activity;

    int error_code = 0;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();
        activity = getActivity();
        sesion = new SocialAppSession(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up,
                container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        binding.btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {

                    toRegister();

                } else {
                    //Mostramos mensaje de error
                    String message = "";
                    switch (error_code) {
                        case 1: {
                            message = "Ingrese un nombre válido";
                            break;
                        }
                        case 2: {
                            message = "Ingrese apellidos válidos";
                            break;
                        }
                        case 3: {
                            message = "Ingrese un email válido";
                            break;
                        }
                        case 4: {
                            message = "Las contraseñas no coinciden";
                            break;
                        }
                    }

                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT)
                            .show();

                }


            }
        });


    }


    public void toRegister() {

        //Mostramos el loader al usuario
        binding.loaders.setVisibility(View.VISIBLE);


        //Campos validos, se procede al registro
        SocialAppLog.getMessage("Registro válido");
        JSONObject parameters = new JSONObject();

        try {

            parameters.put(SocialAppGlobals.api_par_name,
                    binding.txtname.getText().toString());

            parameters.put(SocialAppGlobals.api_par_lastname,
                    binding.txtlastname.getText().toString());

            parameters.put(SocialAppGlobals.api_par_email,
                    binding.txtemail.getText().toString());
            parameters.put(SocialAppGlobals.api_par_password,
                    binding.txtpassword.getText().toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        SocialAppLog.getMessage("url: " + SocialAppGlobals.api_module_user_register);
        SocialAppLog.getMessage("parameters: " + parameters);

        JsonObjectRequest jor =
                new JsonObjectRequest(
                        Request.Method.POST,
                        SocialAppGlobals.api_module_user_register,
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
                                                    sesion.setName(data.getString(SocialAppGlobals.api_res_name));
                                                }
                                                if (data.has(SocialAppGlobals.api_res_lastname)) {
                                                    sesion.setLastname(data.getString(SocialAppGlobals.api_res_lastname));
                                                }
                                                if (data.has(SocialAppGlobals.api_res_email)) {
                                                    sesion.setEmail(data.getString(SocialAppGlobals.api_res_email));
                                                }
                                                if (data.has(SocialAppGlobals.api_res_users_id)) {
                                                    sesion.setId(data.getString(SocialAppGlobals.api_res_users_id));
                                                }

                                                sesion.saveSession();


                                                binding.loaders.setVisibility(View.GONE);

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

                                binding.loaders.setVisibility(View.GONE);
                                Toast.makeText(context, "Error de servidor", Toast.LENGTH_SHORT)
                                        .show();

                            }
                        }
                );

        SocialAppApplication.getInstance().addToRequestQueue(jor);


    }


    public boolean validate() {

        boolean state = false;

        if (!binding.txtpassword.getText().toString().equals("")
                && !binding.txtpassword2.getText().toString().equals("")
                && binding.txtpassword.getText().toString()
                .equals("" + binding.txtpassword2.getText().toString())
                ) {

            if (error_code == 0) {
                state = true;
                error_code = 0;
            }
        } else {
            error_code = 4;
        }

        if (!binding.txtemail.getText().toString().equals("")
                && validateEmail(binding.txtemail.getText().toString())) {
            if (error_code == 0) {
                error_code = 0;
                state = true;
            }
        } else {
            error_code = 3;
        }

        if (!binding.txtlastname.getText().toString().equals("")
                && binding.txtlastname.length() > 3) {
            if (error_code == 0) {
                state = true;
                error_code = 0;
            }
        } else {
            error_code = 2;
        }

        if (!binding.txtname.getText().toString().equals("")
                && binding.txtname.length() > 3) {
            if (error_code == 0) {
                state = true;
                error_code = 0;
            }

        } else {
            error_code = 1;
        }


        return state;
    }


    public boolean validateEmail(String email) {
        return !TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
