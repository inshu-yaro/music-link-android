package com.junkers.musiclink.di;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.junkers.musiclink.adapters.MusicLinkAdapter;
import com.junkers.musiclink.config.Settings;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class MusicLinkAdapterProvider implements Provider<MusicLinkAdapter> {
    @Inject private Settings mSettings;
    @Inject private Gson mGson;

    @Override
    public MusicLinkAdapter get() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(mSettings.getProperty("endpoint"))
                .setConverter(new GsonConverter(mGson))
                .build();
        return restAdapter.create(MusicLinkAdapter.class);
    }
}
