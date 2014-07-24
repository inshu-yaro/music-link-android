package com.junkers.musiclink.managers;

import android.app.Activity;

import com.facebook.Session;
import com.facebook.SessionState;
import com.google.inject.Inject;
import com.junkers.musiclink.adapters.ApiAdapter;
import com.junkers.musiclink.adapters.CacheAdapter;
import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.models.User;
import com.junkers.musiclink.util.log.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.Logger;
import retrofit.RetrofitError;

public class DefaultUserManager implements UserManager {
    @Inject protected ApiAdapter apiAdapter;
    @Inject protected CacheAdapter cacheAdapter;
    private Logger log;

    @Inject
    public DefaultUserManager(LoggerFactory loggerFactory) {
        log = loggerFactory.getLogger(getClass());
    }

    @Override
    public void login(final Activity baseActivity, final Callback<User> callback) {
        Session.openActiveSession(baseActivity, true, new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                if (exception != null) {
                    callback.onFailure();
                }
                if (session.isOpened()) {
                    User user = new User();
                    user.setToken(session.getAccessToken());
                    saveUser(user, callback);
                }
            }
        });
    }

    @Override
    public List<User> loadFriendsList() {
        //unimplemented
        List<User> list = new ArrayList<User>();
        return list;
    }

    private void saveUser(User baseUser, final Callback<User> callback) {
        apiAdapter.createUser(baseUser, new retrofit.Callback<User>() {
            @Override
            public void success(User user, retrofit.client.Response response) {
                cacheAdapter.cacheUser(user);
                callback.onSuccess(user);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onFailure();
            }
        });
    }
}
