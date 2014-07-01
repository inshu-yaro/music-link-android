package com.junkers.musiclink.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.junkers.musiclink.adapters.MusicLinkAdapter;
import com.junkers.musiclink.config.Settings;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class MusicLinkAdapterProvider implements Provider<MusicLinkAdapter> {
    @Inject private Settings mSettings;


    @Override
    public MusicLinkAdapter get() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(mSettings.getProperty("endpoint"))
                .setConverter(new GsonConverter(gson))
                .build();
        return restAdapter.create(MusicLinkAdapter.class);
    }
}
