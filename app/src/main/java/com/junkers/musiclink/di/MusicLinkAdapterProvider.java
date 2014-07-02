package com.junkers.musiclink.di;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.junkers.musiclink.adapters.ApiAdapter;
import com.junkers.musiclink.config.Settings;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class MusicLinkAdapterProvider implements Provider<ApiAdapter> {
    @Inject private Settings mSettings;
    @Inject private Gson mGson;

    @Override
    public ApiAdapter get() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(mSettings.getProperty("endpoint"))
                .setConverter(new GsonConverter(mGson))
                .build();
        return restAdapter.create(ApiAdapter.class);
    }
}
