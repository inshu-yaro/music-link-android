package com.junkers.musiclink.managers;

import android.app.Activity;

import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.models.User;

public interface UserManager {
    public void login(Activity baseActivity, Callback<User> callback);
}
