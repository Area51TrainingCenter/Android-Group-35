package pe.area51.socialapp.helpers.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by segundo on 7/09/17.
 */

public class SocialAppSession {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    String name_pref = "SocialApp";
    String state_pref = "state";
    String user_name_pref = "user_name_pref";
    String user_lastname_pref = "user_lastname_pref";
    String user_email_pref = "user_email_pref";
    String user_id_pref = "user_id_pref";


    public SocialAppSession(Context context) {

        preferences = context.getSharedPreferences(name_pref, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public void saveSession() {
        editor.putBoolean(state_pref, true);
        editor.commit();
    }


    public boolean isLogin() {
        return preferences.getBoolean(state_pref, false);
    }

    /*
     * Nombre de usuario
    */


    public void setName(String name) {
        editor.putString(user_name_pref, name);
        editor.commit();
    }

    public String getName() {
        return preferences.getString(user_name_pref, "");
    }


    /*
     * Apellidos de usuario
    */

    public void setLastname(String lastname) {
        editor.putString(user_lastname_pref, lastname);
        editor.commit();
    }

    public String getLastname() {
        return preferences.getString(user_lastname_pref, "");
    }


    /*
     * Email de usuario
    */

    public void setEmail(String email) {
        editor.putString(user_email_pref, email);
        editor.commit();
    }

    public String getEmail() {
        return preferences.getString(user_email_pref, "");
    }

    /*
     * Id de usuario
    */

    public void setId(String id) {
        editor.putString(user_id_pref, id);
        editor.commit();
    }

    public String getId() {
        return preferences.getString(user_id_pref, "");
    }

}
