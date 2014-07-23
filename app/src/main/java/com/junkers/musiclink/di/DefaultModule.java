package com.junkers.musiclink.di;

import com.google.inject.Binder;
import com.junkers.musiclink.adapters.ApiAdapter;
import com.junkers.musiclink.adapters.CacheAdapter;
import com.junkers.musiclink.adapters.ChatAdapter;
import com.junkers.musiclink.adapters.DefaultQueryAdapter;
import com.junkers.musiclink.adapters.QueryAdapter;
import com.junkers.musiclink.adapters.SharedPrefCacheAdapter;
import com.junkers.musiclink.dummy.adapters.DummyChatAdapter;
import com.junkers.musiclink.managers.DefaultUserManager;
import com.junkers.musiclink.managers.UserManager;
import com.junkers.musiclink.util.log.DevLoggerFactory;
import com.junkers.musiclink.util.log.LoggerFactory;

public class DefaultModule extends BaseModule {

    @Override
    public void configure(Binder binder) {
        // TODO: create production logger factory
        binder.bind(LoggerFactory.class).to(DevLoggerFactory.class).asEagerSingleton();

        super.configure(binder);
        binder.bind(ApiAdapter.class).toProvider(MusicLinkAdapterProvider.class).asEagerSingleton();
        binder.bind(CacheAdapter.class).to(SharedPrefCacheAdapter.class).asEagerSingleton();
        binder.bind(UserManager.class).to(DefaultUserManager.class).asEagerSingleton();
        binder.bind(QueryAdapter.class).to(DefaultQueryAdapter.class).asEagerSingleton();

        binder.bind(ChatAdapter.class).to(DummyChatAdapter.class).asEagerSingleton();
    }
}
