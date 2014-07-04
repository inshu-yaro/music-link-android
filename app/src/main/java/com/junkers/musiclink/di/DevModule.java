package com.junkers.musiclink.di;

import com.google.inject.Binder;
import com.junkers.musiclink.adapters.ApiAdapter;
import com.junkers.musiclink.adapters.CacheAdapter;
import com.junkers.musiclink.adapters.DummyApiAdapter;
import com.junkers.musiclink.adapters.InMemoryCacheAdapter;

public class DevModule extends BaseModule {
    @Override
    public void configure(Binder binder) {
        super.configure(binder);
        binder.bind(ApiAdapter.class).to(DummyApiAdapter.class).asEagerSingleton();
        binder.bind(CacheAdapter.class).to(InMemoryCacheAdapter.class).asEagerSingleton();
    }
}
