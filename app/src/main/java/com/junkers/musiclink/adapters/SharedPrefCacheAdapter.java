package com.junkers.musiclink.adapters;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.models.User;

public class SharedPrefCacheAdapter implements CacheAdapter {
    @Inject private SharedPreferences mSharedPreferences;
    @Inject private Gson mGson;

    public SharedPrefCacheAdapter() {
    }

    public User loadCachedUser() {
        String userJson = mSharedPreferences.getString(User.CACHED_KEY, null);
        if (userJson == null) {
            return null;
        }
        return mGson.fromJson(userJson, User.class);
    }

    public void cacheUser(User user) {
        String serializedUser = mGson.toJson(user);
        mSharedPreferences.edit()
                .putString(User.CACHED_KEY, serializedUser)
                .commit();
    }
}
