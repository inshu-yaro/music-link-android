package com.junkers.musiclink.services;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import roboguice.service.RoboService;

public class MusicPlayerService extends RoboService {
    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }
}
