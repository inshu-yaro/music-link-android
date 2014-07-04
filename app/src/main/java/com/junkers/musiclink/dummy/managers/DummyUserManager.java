package com.junkers.musiclink.dummy.managers;

import android.app.Activity;

import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.managers.UserManager;
import com.junkers.musiclink.models.User;

import org.joda.time.DateTime;

public class DummyUserManager implements UserManager {
    private User getDummyUser() {
        User user = new User();
        user.setFirstName("foo");
        user.setFirstName("bar");
        user.setBirthday(new DateTime(1989, 6, 4, 0, 0));
        user.setToken("foobar");
        user.setFacebookId("123");
        return user;
    }

    @Override
    public void login(Activity baseActivity, Callback<User> callback) {
        callback.onSuccess(getDummyUser());
    }
}
