package com.junkers.musiclink.di;

import com.google.gson.Gson;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.junkers.musiclink.config.Settings;
import com.junkers.musiclink.services.MusicPlayerConnection;

public class BaseModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Settings.class).toProvider(SettingsProvider.class).asEagerSingleton();
        binder.bind(Gson.class).toProvider(GsonProvider.class).asEagerSingleton();
        binder.bind(MusicPlayerConnection.class).asEagerSingleton();
    }
}
