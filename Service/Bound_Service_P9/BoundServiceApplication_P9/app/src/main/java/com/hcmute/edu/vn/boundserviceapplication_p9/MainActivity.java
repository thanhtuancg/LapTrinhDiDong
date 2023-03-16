package com.hcmute.edu.vn.boundserviceapplication_p9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Messenger messenger;
    private boolean isServiceConnection;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            isServiceConnection = true;
            //
            sendMessagePlayMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            isServiceConnection = false;
        }
    };

    private void sendMessagePlayMusic() {
        Message message = Message.obtain(null, MusicService.MSG_PLAY_MUSIC, 0, 0);
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartService = findViewById(R.id.btn_start_service);
        Button btnStopService = findViewById(R.id.btn_stop_service);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStartService();
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStopService();
            }
        });
    }


    private void onClickStartService() {
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void onClickStopService() {
        if(isServiceConnection){
            unbindService(serviceConnection);
            isServiceConnection = false;
        }
    }
}