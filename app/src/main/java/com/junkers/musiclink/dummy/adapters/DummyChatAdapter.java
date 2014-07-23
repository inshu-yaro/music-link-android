package com.junkers.musiclink.dummy.adapters;

import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.adapters.ChatAdapter;
import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.config.Settings;
import com.junkers.musiclink.models.Message;
import com.junkers.musiclink.models.User;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.joda.time.DateTime;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by kazuya on 14/07/22.
 */
public class DummyChatAdapter extends WebSocketClient implements ChatAdapter{

    private Timer mTimer;
    private TimerTask mTimerTask;
    @Inject private Gson mGson;
    private Callback<Message> mCallback;

    @Inject
    public DummyChatAdapter(Settings settings) {
       super(settings.getAsUri("ws_endpoint"));
    }

    @Override
    public void sendMessage(Message message) {
        receiveDummyMessages();
    }

    @Override
    public void registerOnMessageReceived(Callback<Message> callback) {
        mCallback = callback;
    }

    public void receiveDummyMessages(){
       mTimer = new Timer();
       mTimerTask = new TimerTask() {
           @Override
           public void run() {
               User user = new User();
               user.setBirthday(new DateTime());
               user.setFacebookId("0000000");
               user.setFirstName("AAAA");
               user.setLastName("BBBBB");
               user.setToken("token");
               Message msg = new Message();
               msg.setDateTime(new DateTime());
               msg.setMessage("hogehoge");
               msg.setUser(user);
               onMessage(mGson.toJson(msg));
           }
       };
      mTimer.schedule(mTimerTask,5000, 10000);
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
