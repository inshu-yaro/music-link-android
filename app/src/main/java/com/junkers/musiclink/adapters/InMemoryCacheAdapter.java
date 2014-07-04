package com.junkers.musiclink.adapters;

import com.junkers.musiclink.models.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCacheAdapter implements CacheAdapter {
    private Map<String, Object> cachedItems;

    public InMemoryCacheAdapter() {
        cachedItems = new HashMap<String, Object>();
    }

    @Override
    public User loadCachedUser() {
        if (cachedItems.containsKey(User.CACHED_KEY)) {
            return (User) cachedItems.get(User.CACHED_KEY);
        } else {
            return new User();
        }
    }

    @Override
    public void cacheUser(User user) {
        cachedItems.put(User.CACHED_KEY, user);
    }
}
