package com.junkers.musiclink.adapters;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.config.Settings;
import com.junkers.musiclink.models.Message;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * Created by kazuya on 14/07/22.
 */
public class WSChatAdapter extends WebSocketClient implements ChatAdapter{

    @Inject
    private Gson mGson;
    private Callback<Message> mCallback;

    @Inject
    public WSChatAdapter(Settings settings) {
        super(settings.getAsUri("ws_endpoint"));
    }

    @Override
    public void sendMessage(Message message) {

    }

    @Override
    public void registerOnMessageReceived(Callback<Message> callback) {
        mCallback = callback;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }

    @Override
    public void onMessage(String message) {
        Message msg = mGson.fromJson(message, Message.class);
        if(mCallback!=null){
            mCallback.onSuccess(msg);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }
}
