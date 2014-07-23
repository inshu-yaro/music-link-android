package com.junkers.musiclink.adapters;

import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.models.Message;
import com.junkers.musiclink.models.User;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ChatAdapter {

    void sendMessage(Message message);
    void registerOnMessageReceived(Callback<Message> callback);
}
