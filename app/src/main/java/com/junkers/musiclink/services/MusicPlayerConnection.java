package com.junkers.musiclink.services;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.google.inject.Inject;
import com.junkers.musiclink.util.log.LoggerFactory;

import ch.qos.logback.classic.Logger;


public class MusicPlayerConnection implements ServiceConnection {
    private boolean mBound = false;
    private MusicPlayerService mPlayerService;
    private Logger log;

    @Inject
    public MusicPlayerConnection(LoggerFactory loggerFactory) {
        log = loggerFactory.getLogger(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicPlayerService.LocalBinder binder = (MusicPlayerService.LocalBinder) service;
        mPlayerService = binder.getService();
        mBound = true;
        log.info("Music player service bound");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mBound = false;
    }

    public boolean isBound() {
        return mBound;
    }

    public void setBound(boolean bound) {
        mBound = bound;
    }

    public MusicPlayerService getPlayerService() {
        return mPlayerService;
    }
}
