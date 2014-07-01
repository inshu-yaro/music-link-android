package com.junkers.musiclink.adapters;

import com.junkers.musiclink.models.User;

import retrofit.http.GET;
import retrofit.http.Path;

public interface MusicLinkAdapter {
    @GET("/users/{token}")
    User getUser(@Path("token") String token);
}
