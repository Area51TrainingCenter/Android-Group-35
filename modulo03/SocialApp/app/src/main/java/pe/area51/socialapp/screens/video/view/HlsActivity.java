package pe.area51.socialapp.screens.video.view;

import android.app.ProgressDialog;
import android.content.Context;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;

import android.widget.VideoView;

import pe.area51.socialapp.R;
import pe.area51.socialapp.helpers.analytics.SocialAppAnalytics;
import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.helpers.session.SocialAppSession;
import pe.area51.socialapp.widgets.activity.SocialAppActivity;

public class HlsActivity extends SocialAppActivity
        implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {

    SocialAppSession session;
    Context context;


    ProgressDialog progress;
    MediaController media;
    VideoView video;

    String video_url = "https://cdn.theoplayer.com/video/star_wars_episode_vii-the_force_awakens_official_comic-con_2015_reel_(2015)/index.m3u8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hls);

        context = this;
        session = new SocialAppSession(context);

        SocialAppAnalytics.trackingScreen(getApplication(), "video-hls", session);

        //Si recibimos la url de video
        //video_url = getIntent().getExtras().getString("video")

        video = (VideoView) findViewById(R.id.video);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (media == null) {
            media = new MediaController(this);
        }

        if (progress == null) {
            progress = new ProgressDialog(this);
        }

        //Configuramos el progress
        progress.setMessage("Cargando...");
        progress.setCancelable(false);
        progress.show();

        video.setMediaController(media);
        //video.setVideoPath(); //Url interna del app
        video.setVideoURI(Uri.parse(video_url)); //Url de internet

        video.setOnPreparedListener(this);
        video.setOnErrorListener(this);
        video.setOnCompletionListener(this);


    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

        progress.dismiss();
        mediaPlayer.start();

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        SocialAppLog.getMessage("Error");
        //Liberamos el recurso del mediaController

        /*
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        */

        finish();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        SocialAppLog.getMessage("onCompletion");
        /*
        //Liberamos el recurso del mediaController
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        */


        finish();
    }
}
