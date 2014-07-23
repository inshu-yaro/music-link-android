package com.junkers.musiclink.services;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.junkers.musiclink.adapters.ChatAdapter;
import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.dummy.adapters.DummyChatAdapter;
import com.junkers.musiclink.models.Message;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by kazuya on 14/07/22.
 */
public class ChatService extends BaseService {

    private final IBinder mBinder = new LocalBinder();
    @Inject private ChatAdapter mChatAdapter;
    @Inject private NotificationManager mNotificationManager;

    @Override
    public void onCreate(){
        super.onCreate();
        log.debug("chat service created");
        mChatAdapter.registerOnMessageReceived(new Callback<Message>() {
            @Override
            public void onSuccess(Message message) {
                log.debug(message.getMessage());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class LocalBinder extends Binder {
        public ChatService getService() {
            return ChatService.this;
        }
    }

}
