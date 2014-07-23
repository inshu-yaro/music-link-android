package com.junkers.musiclink.app;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.app.base.BaseActivity;
import com.junkers.musiclink.models.Song;
import com.junkers.musiclink.services.MusicPlayerConnection;
import com.junkers.musiclink.services.MusicPlayerService;

import roboguice.inject.InjectView;

public class ChatActivity extends BaseActivity {
    public static final String USER_EXTRA_KEY = "user";

    @InjectView(R.id.artist_name_text_view) private TextView mArtistNameTextView;
    @InjectView(R.id.song_title_text_view) private TextView mSongTitleTextView;
    @Inject private MusicPlayerConnection mPlayerConnection;
    @Inject private Gson mGson;
    private MusicPlayerService mPlayerService;
    private Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setupActionBar();
    }

    private void displaySongInfo() {
        mArtistNameTextView.setText(song.getArtist().getName());
        mSongTitleTextView.setText(song.getTitle());
    }

    private void setupActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
