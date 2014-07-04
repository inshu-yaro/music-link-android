package com.junkers.musiclink.di;

import com.google.inject.Binder;
import com.junkers.musiclink.adapters.ApiAdapter;
import com.junkers.musiclink.adapters.CacheAdapter;
import com.junkers.musiclink.adapters.SharedPrefCacheAdapter;
import com.junkers.musiclink.managers.DefaultUserManager;
import com.junkers.musiclink.managers.UserManager;

public class DefaultModule extends BaseModule {

    @Override
    public void configure(Binder binder) {
        super.configure(binder);
        binder.bind(ApiAdapter.class).toProvider(MusicLinkAdapterProvider.class).asEagerSingleton();
        binder.bind(CacheAdapter.class).to(SharedPrefCacheAdapter.class).asEagerSingleton();
        binder.bind(UserManager.class).to(DefaultUserManager.class).asEagerSingleton();
    }
}
