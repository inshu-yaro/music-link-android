package com.junkers.musiclink.di;

import com.google.inject.Binder;
import com.junkers.musiclink.adapters.ApiAdapter;
import com.junkers.musiclink.adapters.CacheAdapter;
import com.junkers.musiclink.dummy.adapters.DummyApiAdapter;
import com.junkers.musiclink.dummy.adapters.DummyCacheAdapter;
import com.junkers.musiclink.dummy.managers.DummyUserManager;
import com.junkers.musiclink.managers.UserManager;

public class DevModule extends BaseModule {
    @Override
    public void configure(Binder binder) {
        super.configure(binder);
        binder.bind(ApiAdapter.class).to(DummyApiAdapter.class).asEagerSingleton();
        binder.bind(CacheAdapter.class).to(DummyCacheAdapter.class).asEagerSingleton();
        binder.bind(UserManager.class).to(DummyUserManager.class).asEagerSingleton();
    }
}
