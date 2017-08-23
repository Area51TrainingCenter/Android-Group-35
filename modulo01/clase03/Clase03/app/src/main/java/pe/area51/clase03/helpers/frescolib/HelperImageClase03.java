package pe.area51.clase03.helpers.frescolib;


import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import pe.area51.clase03.helpers.log.HelperLog;

/**
 * Created by segundo on 17/08/17.
 */

public class HelperImageClase03 {

    public String url = "";
    public SimpleDraweeView view;
    Context context;

    public HelperImageClase03(Context context) {
        this.context = context;
    }

    public void show() {

        HelperLog.getMessage("photo: " + url);

        DraweeController ctrl = Fresco
                .newDraweeControllerBuilder()
                .setUri(Uri.parse(url))
                .setTapToRetryEnabled(true).build();


        view.setController(ctrl);
    }


}
