package pe.area51.socialapp.screens.login.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pe.area51.socialapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    View view;
    EditText txtpassword, txtemail;
    Button btnlogin;

    public LoginFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);

        txtemail = (EditText) view.findViewById(R.id.txtemail);
        txtpassword = (EditText) view.findViewById(R.id.txtpassword);
        btnlogin = (Button) view.findViewById(R.id.btnlogin);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





    }
}
