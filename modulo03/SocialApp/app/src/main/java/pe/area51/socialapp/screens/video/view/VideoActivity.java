package pe.area51.socialapp.screens.video.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pe.area51.socialapp.R;
import pe.area51.socialapp.helpers.analytics.SocialAppAnalytics;
import pe.area51.socialapp.helpers.session.SocialAppSession;

public class VideoActivity extends AppCompatActivity {

    SocialAppSession session;
    Context context;

    Button btnhls, btnvideo, btnsurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        context = this;
        session = new SocialAppSession(context);

        SocialAppAnalytics.trackingScreen(getApplication(), "video", session);

        btnhls = (Button) findViewById(R.id.btnhls);
        btnvideo = (Button) findViewById(R.id.btnvideo);
        btnsurface = (Button) findViewById(R.id.btnsurface);

        btnhls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Seteamos el evento en analytics
                SocialAppAnalytics.trackingAction(
                        getApplication(),
                        "video",
                        "video",
                        "video-hls"
                );

                Intent intent = new Intent(VideoActivity.this, HlsActivity.class);
                startActivity(intent);

            }
        });


        btnvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Seteamos el evento en analytics
                SocialAppAnalytics.trackingAction(
                        getApplication(),
                        "video",
                        "video",
                        "video-videoview"
                );


                Intent intent = new Intent(VideoActivity.this, VideoviewActivity.class);
                startActivity(intent);
            }
        });


        btnsurface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Seteamos el evento en analytics
                SocialAppAnalytics.trackingAction(
                        getApplication(),
                        "video",
                        "video",
                        "video-surfaceview"
                );

                Intent intent = new Intent(VideoActivity.this, SurfaceActivity.class);
                startActivity(intent);

            }
        });


    }
}
