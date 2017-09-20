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

    public SocialAppSession(Context context) {

        preferences = context.getSharedPreferences(name_pref, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public boolean isLogin() {

        return preferences.getBoolean(state_pref, false);

    }


}
