package pe.area51.clase02b;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt;
    TextView title;
    Button btn;

    //Contexto o momento de vida de la Actividad
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        //Relacionamos las variables a los componentes visuales
        title = (TextView) findViewById(R.id.title);
        txt = (EditText) findViewById(R.id.txt);
        btn = (Button) findViewById(R.id.btn);


        //btn.setOnClickListener(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cadena = txt.getText().toString();

                if (!cadena.equals("")) {
                    cadena = title.getText() + " \n" + cadena;
                    title.setText(cadena);
                    txt.setText("");

                } else {
                    /*
                    Toast.makeText(context,
                            getResources().getString(R.string.welcome_error)
                            , Toast.LENGTH_SHORT);
                    */

                    Toast.makeText(context,
                            R.string.welcome_error
                            , Toast.LENGTH_SHORT)
                            .show();

                }


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /*
    @Override
    public void onClick(View view) {

    }
    */
}
