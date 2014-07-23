package com.junkers.musiclink.services;

import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.junkers.musiclink.adapters.ChatAdapter;
import com.junkers.musiclink.dummy.adapters.DummyChatAdapter;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by kazuya on 14/07/22.
 */
public class ChatService extends BaseService {

    @Inject private ChatAdapter mChatAdapter;

    @Override
    public void onCreate(){
        super.onCreate();
        log.debug("chat service created");
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
