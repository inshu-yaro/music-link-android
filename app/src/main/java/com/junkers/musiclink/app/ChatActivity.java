package com.junkers.musiclink.app;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.adapters.ChatAdapter;
import com.junkers.musiclink.app.base.BaseActivity;
import com.junkers.musiclink.models.Message;
import com.junkers.musiclink.models.Song;
import com.junkers.musiclink.models.User;
import com.junkers.musiclink.services.ChatConnection;
import com.junkers.musiclink.services.ChatService;
import com.junkers.musiclink.services.MusicPlayerConnection;
import com.junkers.musiclink.services.MusicPlayerService;

import org.joda.time.DateTime;

import roboguice.inject.InjectView;

public class ChatActivity extends BaseActivity implements View.OnClickListener{
    public static final String USER_EXTRA_KEY = "user";

    @Inject private Gson mGson;
    @Inject private ChatAdapter mChatAdapter;
    @Inject private ChatConnection mChatConnection;
    private EditText mEditText;
    private Button mButton;
    private User mUser;
    private ChatService mChatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setupActionBar();
        mEditText = (EditText) findViewById(R.id.edit_text);
        mButton = (Button) findViewById(R.id.submit_button);
        mButton.setOnClickListener(this);
        mUser = getFriend();
        mChatService = mChatConnection.getService();
    }

    private User getFriend(){
        return mGson.fromJson(getIntent().getStringExtra(USER_EXTRA_KEY), User.class);
    }

    private void setupActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit_button:
                mChatAdapter.sendMessage(Message.getMessageObject(mEditText.getText().toString(), new DateTime(), mUser));
                mEditText.getEditableText().clear();
                break;

            default:
                break;
        }
    }
}
