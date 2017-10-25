package pe.area51.socialapp.screens.video.view;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import pe.area51.socialapp.R;
import pe.area51.socialapp.helpers.analytics.SocialAppAnalytics;
import pe.area51.socialapp.helpers.log.SocialAppLog;
import pe.area51.socialapp.helpers.session.SocialAppSession;

public class SurfaceActivity extends AppCompatActivity
        implements SurfaceHolder.Callback,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnInfoListener,
        MediaPlayer.OnVideoSizeChangedListener,
        MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnErrorListener {

    Context context;
    SocialAppSession session;

    SurfaceView surface;
    SurfaceHolder holder;
    MediaPlayer player;

    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);

        context = this;
        session = new SocialAppSession(context);

        SocialAppAnalytics.trackingScreen(getApplication(), "video-surfaceview", session);

        surface = (SurfaceView) findViewById(R.id.surface);

        url = "android.resource://" + getPackageName() + "/" + R.raw.video;
        //android.resource://pe.area51.socialapp/2131165184
        SocialAppLog.getMessage("url: " + url);

        onStartVideo();

    }

    public void onStartVideo() {

        holder = surface.getHolder();
        holder.addCallback(this);

        player = new MediaPlayer();

        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnInfoListener(this);
        player.setOnVideoSizeChangedListener(this);
        player.setOnBufferingUpdateListener(this);
        player.setOnErrorListener(this);

        //Para el audio
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {

            player.setDataSource(this, Uri.parse(url));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        player.setDisplay(surfaceHolder);

        try {

            player.prepare(); //Interno

        } catch (IOException e) {
            e.printStackTrace();
        }

        //player.prepareAsync(); //Externo

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

        mediaPlayer.start();

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }
}
