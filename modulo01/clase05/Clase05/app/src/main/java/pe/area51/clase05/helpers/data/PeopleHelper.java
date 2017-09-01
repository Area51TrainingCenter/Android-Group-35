package pe.area51.clase05.helpers.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import pe.area51.clase05.models.PersonModel;

/**
 * Created by segundo on 31/08/17.
 */

public class PeopleHelper {

    public static ArrayList<PersonModel> getPeople() {

        ArrayList<PersonModel> people = new ArrayList<PersonModel>();

        for (int i = 0; i < 30; i++) {

            //Instanciamos el momento
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            //Número aleatorio
            Random random = new Random();
            PersonModel pm = new PersonModel();

            pm.setName("Elemento " + i);
            pm.setPhoto("http://segundoacosta.com/people/img_" + i + ".jpg");
            pm.setMessage("");
            pm.setState(random.nextInt(1));
            pm.setTime("" + sdf.format(cal.getTime())); //Momento actual
            pm.setMessage_count(random.nextInt(30)); //Número aleatorio

            //agregamos el objeto al arreglo
            people.add(pm);

        }

        return people;
    }


    public PersonModel getItem() {
        PersonModel pm = new PersonModel();

        return pm;
    }

    public String getName() {

        return "name";
    }

    public void OtherMethod() {

    }


}
