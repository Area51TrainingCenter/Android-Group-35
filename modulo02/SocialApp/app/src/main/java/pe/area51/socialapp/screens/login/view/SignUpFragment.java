package pe.area51.socialapp.screens.login.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.area51.socialapp.R;
import pe.area51.socialapp.databinding.FragmentSignUpBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    FragmentSignUpBinding binding;

    int error_code = 0;


    public SignUpFragment() {
        // Required empty public constructor
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
                    //Campos validos, se procede al registro

                }else{
                    //Mostramos mensaje de error

                    switch (error_code){

                    }

                }


            }
        });



    }

    public boolean validate() {

        boolean state = false;

        if (!binding.txtname.getText().toString().equals("")
                && binding.txtname.length() > 3) {
            state = true;
        }else{
            error_code = 1;
        }

        if (!binding.txtlastname.getText().toString().equals("")
                && binding.txtlastname.length() > 3) {
            state = true;
        }else{
            error_code = 2;
        }

        if (!binding.txtemail.getText().toString().equals("")
                && validateEmail(binding.txtemail.getText().toString())) {
            state = true;
        }else{
            error_code = 3;
        }


        if (!binding.txtpassword.getText().toString().equals("")
                && !binding.txtpassword2.getText().toString().equals("")
                && binding.txtpassword.getText().toString()
                .equals(binding.txtpassword2.getText().toString())
                ) {

            state = true;
        }else{
            error_code = 4;
        }


        return state;
    }


    public boolean validateEmail(String email) {
        return !TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
