package com.hcmute.edu.vn.boundserviceapplication_p9;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;


public class MusicService extends Service {

    public static final int MSG_PLAY_MUSIC = 1;
    private MediaPlayer mediaPlayer;
    private Messenger messenger;

    public class MyHandler extends Handler{
        private Context applicationContext;

        public MyHandler(Context context){
            this.applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_PLAY_MUSIC:
                    startMusic();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MusicService", "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MusicService", "onBind");
        messenger = new Messenger(new MyHandler(this));
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MusicService", "onUnBind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MusicService", "onDestroy");
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void startMusic(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        }
        mediaPlayer.start();
    }
}
