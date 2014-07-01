package com.junkers.musiclink.di;

import android.content.Context;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.junkers.musiclink.config.Settings;

public class BaseModule implements Module {
    private Context mContext;

    public BaseModule(Context context) {
        mContext = context;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(Settings.class).toProvider(new SettingsProvider(mContext));
        binder.bind(Context.class).toInstance(mContext);
    }
}
