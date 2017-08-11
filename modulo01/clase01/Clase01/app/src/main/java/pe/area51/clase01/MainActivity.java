package pe.area51.clase01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int variableClase = 0;
    int variableMetodo = 0;

    public String uno;
    private String dos;
    protected String tres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int variableMetodo = 0;
        this.variableMetodo = 1;

        variableMetodo = 2;


        /*
        int total = arreglo.size;

        for (int i = 0; i < total; i++ ){

        }



        for (int i = 0; i < arreglo.size; i++ ){

        }
        */


    }

    public String cadena(){

        return "cadena";
    }


    private void cadena(int cadena){

        MainActivity.numero();

    }


    static void numero(){

    }

}
