package com.junkers.musiclink.adapters;

import com.junkers.musiclink.models.User;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface MusicLinkAdapter {
    @GET("/users/{token}")
    void getUser(@Path("token") String token, Callback<User> user);
}
