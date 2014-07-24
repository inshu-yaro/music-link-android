package com.junkers.musiclink.di;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.junkers.musiclink.adapters.ApiAdapter;
import com.junkers.musiclink.adapters.CacheAdapter;
import com.junkers.musiclink.config.Settings;
import com.junkers.musiclink.models.User;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class MusicLinkAdapterProvider implements Provider<ApiAdapter> {
    @Inject private Settings mSettings;
    @Inject private Gson mGson;
    @Inject private CacheAdapter mCacheAdapter;

    @Override
    public ApiAdapter get() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(mSettings.getProperty("endpoint"))
                .setConverter(new GsonConverter(mGson))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        // FIXME: use proper authentication process
                        User user = mCacheAdapter.loadCachedUser();
                        if (user != null) {
                            request.addHeader("X-Token", user.getToken());
                        }
                    }
                })
                .build();
        return restAdapter.create(ApiAdapter.class);
    }
}
