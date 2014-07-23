package com.junkers.musiclink.di;

import com.google.inject.Binder;
import com.junkers.musiclink.adapters.ApiAdapter;
import com.junkers.musiclink.adapters.CacheAdapter;
import com.junkers.musiclink.adapters.ChatAdapter;
import com.junkers.musiclink.adapters.QueryAdapter;
import com.junkers.musiclink.adapters.WSChatAdapter;
import com.junkers.musiclink.dummy.adapters.DummyApiAdapter;
import com.junkers.musiclink.dummy.adapters.DummyCacheAdapter;
import com.junkers.musiclink.dummy.adapters.DummyChatAdapter;
import com.junkers.musiclink.dummy.adapters.DummyQueryAdapter;
import com.junkers.musiclink.dummy.managers.DummyUserManager;
import com.junkers.musiclink.managers.UserManager;
import com.junkers.musiclink.util.log.DevLoggerFactory;
import com.junkers.musiclink.util.log.LoggerFactory;

public class DevModule extends BaseModule {
    @Override
    public void configure(Binder binder) {
        binder.bind(LoggerFactory.class).to(DevLoggerFactory.class).asEagerSingleton();

        super.configure(binder);

        binder.bind(ApiAdapter.class).to(DummyApiAdapter.class).asEagerSingleton();
        binder.bind(CacheAdapter.class).to(DummyCacheAdapter.class).asEagerSingleton();
        binder.bind(UserManager.class).to(DummyUserManager.class).asEagerSingleton();
        binder.bind(QueryAdapter.class).to(DummyQueryAdapter.class).asEagerSingleton();
        binder.bind(ChatAdapter.class).to(DummyChatAdapter.class).asEagerSingleton();

    }
}
