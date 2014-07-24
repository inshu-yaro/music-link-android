package com.junkers.musiclink.adapters;

import com.junkers.musiclink.models.Song;
import com.junkers.musiclink.models.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ApiAdapter {
    @GET("/users/{token}")
    void getUser(@Path("token") String token, Callback<User> callback);

    @POST("/users")
    void createUser(@Body User user, Callback<User> callback);

    @POST("/songs/save_play")
    void saveSongPlay(@Body Song song, Callback<?> callback);
}
