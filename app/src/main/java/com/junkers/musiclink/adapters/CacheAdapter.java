package com.junkers.musiclink.adapters;

import com.junkers.musiclink.models.User;

public interface CacheAdapter {
    User loadCachedUser();
    void cacheUser(User user);
}
