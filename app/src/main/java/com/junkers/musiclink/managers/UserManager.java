package com.junkers.musiclink.managers;

import android.app.Activity;

import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.models.User;

import java.util.List;

public interface UserManager {
    void login(Activity baseActivity, Callback<User> callback);
    List<User> loadFriendsList();
}
