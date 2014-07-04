package com.junkers.musiclink.dummy.adapters;

import com.junkers.musiclink.adapters.ApiAdapter;
import com.junkers.musiclink.models.User;

import retrofit.Callback;
import retrofit.client.Response;

public class DummyApiAdapter implements ApiAdapter {
    private Response getDummyResponse(int code) {
        return new Response("", code, "ok", null, null);
    }

    @Override
    public void getUser(String token, Callback<User> callback) {
        User user = new User();
        user.setToken("foobar");
        callback.success(user, this.getDummyResponse(201));
    }

    @Override
    public void createUser(User user, Callback<User> callback) {
        callback.success(user, this.getDummyResponse(201));
    }
}
