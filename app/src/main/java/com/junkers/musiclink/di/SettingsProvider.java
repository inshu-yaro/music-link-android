package com.junkers.musiclink.di;

import android.content.Context;

import com.google.inject.Provider;
import com.junkers.musiclink.config.Settings;

import java.io.IOException;
import java.io.InputStream;

public class SettingsProvider implements Provider<Settings> {
    private Context mContext;

    public SettingsProvider(Context context) {
        mContext = context;
    }

    @Override
    public Settings get() {
        Settings settings = new Settings();
        try {
            InputStream propertiesStream = mContext.getAssets().open("app.properties");
            settings.load(propertiesStream);
        } catch (IOException e) {
            settings.loadDefaults();
        }
        return settings;
    }
}
