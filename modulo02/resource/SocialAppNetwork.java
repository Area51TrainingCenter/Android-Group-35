package pe.area51.socialapp.helpers.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by segundo on 3/10/17.
 */

public class SocialAppNetwork {

    Context context;
    NetworkInfo network;
    boolean wifiConnection = false;
    boolean MobileConnection = false;

    public SocialAppNetwork(Context context) {
        super();
        this.context = context;
    }

    public boolean getNetwork() {

        ConnectivityManager con = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        network = con.getActiveNetworkInfo();

        if (network != null && network.isConnected()) {

            wifiConnection = network.getType() == ConnectivityManager.TYPE_WIFI;
            MobileConnection = network.getType() == ConnectivityManager.TYPE_MOBILE;

            if (wifiConnection) {
                return true;
            } else if (MobileConnection) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

}
