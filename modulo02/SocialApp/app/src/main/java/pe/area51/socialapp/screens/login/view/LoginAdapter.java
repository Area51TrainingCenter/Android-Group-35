package pe.area51.socialapp.screens.login.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by segundo on 12/09/17.
 */

public class LoginAdapter extends FragmentStatePagerAdapter {


    public LoginAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new LoginFragment();
        } else {
            fragment = new SignUpFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
