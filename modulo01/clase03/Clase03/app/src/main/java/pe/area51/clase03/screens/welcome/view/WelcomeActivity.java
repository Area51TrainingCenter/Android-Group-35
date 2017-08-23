package pe.area51.clase03.screens.welcome.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import pe.area51.clase03.Clase03Globals;
import pe.area51.clase03.R;
import pe.area51.clase03.models.UserModel;

public class WelcomeActivity extends AppCompatActivity {

    Context context;

    UserAdapter adapter;
    ListView users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        users = (ListView)findViewById(R.id.users);

        context = this;
        loadUsers();

    }

    public void loadUsers() {

        if (Clase03Globals.lista == null) {
            Clase03Globals.lista = new ArrayList<UserModel>();
        }

        for (int i = 0; i < 100; i++) {

            //Creamos un objeto
            UserModel user = new UserModel();
            //Le enviamos la información de las propiedades del objeto
            user.setName("Nombre " + i);
            user.setDescription("Descripción" + i);

            //Añadimos el objeto al arreglo
            Clase03Globals.lista.add(user);

        }


        adapter = new UserAdapter(context, Clase03Globals.lista);
        users.setAdapter(adapter);


    }


}
