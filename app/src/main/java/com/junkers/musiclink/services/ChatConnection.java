package com.junkers.musiclink.services;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.google.inject.Inject;
import com.junkers.musiclink.util.log.LoggerFactory;

import ch.qos.logback.classic.Logger;


public class ChatConnection implements ServiceConnection {
    private boolean mBound = false;
    private ChatService mService;
    private Logger log;

    @Inject
    public ChatConnection(LoggerFactory loggerFactory) {
        log = loggerFactory.getLogger(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        ChatService.LocalBinder binder = (ChatService.LocalBinder) service;
        mService = binder.getService();
        mBound = true;
        log.info("Chat service bound");
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

    public ChatService getService() {
        return mService;
    }
}
