package pe.area51.socialapp.helpers.analytics;

import android.app.Application;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import pe.area51.socialapp.SocialAppApplication;
import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.helpers.session.SocialAppSession;

/**
 * Created by segundo on 12/10/17.
 */

public class SocialAppAnalytics {

    public static void trackingScreen(Application application, String screen, SocialAppSession sesion) {

        SocialAppLog.getMessage("trackingScreen: screen: " + screen);

        SocialAppApplication app = (SocialAppApplication) application;
        Tracker tracker = app.getDefaultTracker();
        tracker.setScreenName(screen);

        //tracker.set("&uid", sesion.getAg_user_id());
        //tracker.set("cd1", sesion.getUnique_id());

        tracker.send(new HitBuilders
                .ScreenViewBuilder()
                .build());

    }

    public static void trackingAction(Application application, String category, String action, String label, SocialAppSession sesion) {

        SocialAppLog.getMessage("trackingAction category: " + category + " action: " + action + " label: " + label);

        SocialAppApplication app = (SocialAppApplication) application;
        Tracker tracker = app.getDefaultTracker();

        tracker.send(
                new HitBuilders
                        .EventBuilder()
                        .setCategory(category)
                        .setAction(action)
                        .setLabel(label)
                        .build());
    }


}
