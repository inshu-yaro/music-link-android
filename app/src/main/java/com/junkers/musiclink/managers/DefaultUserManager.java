package com.junkers.musiclink.managers;

import android.app.Activity;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.google.inject.Inject;
import com.junkers.musiclink.adapters.ApiAdapter;
import com.junkers.musiclink.adapters.CacheAdapter;
import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

public class DefaultUserManager implements UserManager {
    @Inject protected ApiAdapter apiAdapter;
    @Inject protected CacheAdapter cacheAdapter;

    @Override
    public void login(final Activity baseActivity, final Callback<User> callback) {
        Session.openActiveSession(baseActivity, true, new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                if (exception != null) {
                    callback.onFailure();
                }
                if (session.isOpened()) {
                    addBirthdayPermission(session, baseActivity);
                    loadUser(session, callback);
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

    private void loadUser(final Session session, final Callback<User> callback) {
        loadFromFacebook(session, new Callback<User>() {
            @Override
            public void onSuccess(User user) {
                user.setToken(session.getAccessToken());
                saveUser(user, callback);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
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

    private void addBirthdayPermission(Session session, Activity baseActivity) {
        if (!session.getPermissions().contains("user_birthday")) {
            List<String> newPermission = new ArrayList<String>();
            newPermission.add("user_birthday");
            Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(baseActivity, newPermission);
            session.requestNewReadPermissions(newPermissionsRequest);
        }
    }

    private void loadFromFacebook(Session session, final Callback<User> callback) {
        Request.newMeRequest(session, new Request.GraphUserCallback() {
            @Override
            public void onCompleted(GraphUser user, Response response) {
                if (response.getError() == null) {
                    callback.onSuccess(User.fromGraphUser(user));
                } else {
                    callback.onFailure();
                }
            }
        }).executeAsync();
    }
}
