package pe.area51.socialapp.widgets.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.w3c.dom.Text;

import pe.area51.socialapp.R;

/**
 * Created by segundo on 7/09/17.
 */

public class SocialAppActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView title;

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.title);
    }

    public void setTitle(String title) {
        if (this.title != null) {
            this.title.setText(title);
        }
    }


}
