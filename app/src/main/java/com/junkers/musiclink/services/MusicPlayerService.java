package com.junkers.musiclink.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.app.MainActivity;
import com.junkers.musiclink.models.Song;

import java.io.IOException;

public class MusicPlayerService extends BaseService {
    @Inject private NotificationManager mNotificationManager;
    @Inject private Gson mGson;

    private int NOTIFICATION = R.string.music_notification;
    private Song mCurrentSong;


    private MediaPlayer mPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNotificationManager.cancel(NOTIFICATION);
        mPlayer.stop();
        mPlayer.release();
    }

    public void playSong(Song song) {
        log.debug("Starting to play song: " + song.getTitle() + " with path " + song.getPath());
        mPlayer.reset();
        try {
            mPlayer.setDataSource(song.getPath());
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.start();
        mCurrentSong = song;
        showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final MusicPlayerRemoteService.Stub mBinder = new MusicPlayerRemoteService.Stub() {
        @Override
        public void playSong(String songJson) throws RemoteException {
            MusicPlayerService.this.playSong(mGson.fromJson(songJson, Song.class));
        }
    };

//    public class LocalBinder extends Binder {
//        public MusicPlayerService getService() {
//            return MusicPlayerService.this;
//        }
//    }

    private void showNotification() {
        if (mCurrentSong == null)
            return;
        CharSequence text = mCurrentSong.getTitle();

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(mCurrentSong.getTitle())
                .setSmallIcon(R.drawable.play)
                .setContentIntent(contentIntent)
                .setOngoing(true)
                .build();


        mNotificationManager.notify(NOTIFICATION, notification);
    }
}
