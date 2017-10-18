package pe.area51.socialapp.helpers.firebase.notifications;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.helpers.session.SocialAppSession;

/**
 * Created by segundo on 17/10/17.
 */

public class SocialAppInstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        SocialAppLog.getMessage("token: " + token);

        SocialAppSession session = new SocialAppSession(getApplicationContext());

        session.setFirebase_token(token);

        SocialAppLog.getMessage("token: " + session.getFirebase_token());
        
    }
}
