package pe.area51.socialapp.helpers.frescolib;

import android.content.Context;

import com.facebook.drawee.view.SimpleDraweeView;

import pe.area51.socialapp.helpers.log.SocialAppLog;

/**
 * Created by segundo on 7/09/17.
 */

public class SocialAppImage {


    public SimpleDraweeView view;
    public String url;

    Context context;

    public SocialAppImage(Context context) {
        this.context = context;
    }

    public void show() {

        SocialAppLog.getMessage("SocialAppImage: " + url);

    }


}
