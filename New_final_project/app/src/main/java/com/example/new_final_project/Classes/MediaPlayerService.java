package com.example.new_final_project.Classes;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

import com.example.new_final_project.R;

public class MediaPlayerService extends Service {

    private android.media.MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //media player
//        player = android.media.MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player = android.media.MediaPlayer.create(this, R.raw.cvish_hahof);

        player.setLooping(true);
        player.start();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}
