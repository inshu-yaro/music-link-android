package com.junkers.musiclink.di;

import android.content.res.AssetManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.junkers.musiclink.config.Settings;

import java.io.IOException;
import java.io.InputStream;

public class SettingsProvider implements Provider<Settings> {
    @Inject private AssetManager mAssetManager;

    @Override
    public Settings get() {
        Settings settings = new Settings();
        try {
            InputStream propertiesStream = mAssetManager.open("app.properties");
            settings.load(propertiesStream);
        } catch (IOException e) {
            settings.loadDefaults();
        }
        return settings;
    }
}
