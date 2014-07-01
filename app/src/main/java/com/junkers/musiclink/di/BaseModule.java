package com.junkers.musiclink.di;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.junkers.musiclink.adapters.MusicLinkAdapter;
import com.junkers.musiclink.config.Settings;

public class BaseModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Settings.class).toProvider(SettingsProvider.class).asEagerSingleton();
        binder.bind(MusicLinkAdapter.class).toProvider(MusicLinkAdapterProvider.class).asEagerSingleton();
    }
}
