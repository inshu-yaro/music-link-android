package com.junkers.musiclink;

import android.app.Application;

import com.google.inject.Module;
import com.junkers.musiclink.di.DefaultModule;
import com.junkers.musiclink.di.DevModule;

import roboguice.RoboGuice;

public class MusicLinkApp extends Application {
    private static String environment;

    @Override
    public void onCreate() {
        super.onCreate();

        environment = getResources().getString(R.string.environment);

        Module module;

        if (getEnvironment().equals("production")) {
            module = new DefaultModule();
        } else {
            module = new DevModule();
        }

        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), module);
    }

    public static String getEnvironment() {
        return environment;
    }
}
