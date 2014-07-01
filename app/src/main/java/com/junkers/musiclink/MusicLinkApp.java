package com.junkers.musiclink;

import android.app.Application;

import com.junkers.musiclink.di.BaseModule;

import roboguice.RoboGuice;

public class MusicLinkApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), new BaseModule());

    }
}
