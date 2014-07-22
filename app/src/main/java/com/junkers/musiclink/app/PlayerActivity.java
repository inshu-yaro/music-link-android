package com.junkers.musiclink.app;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.app.base.BaseActivity;
import com.junkers.musiclink.models.Song;
import com.junkers.musiclink.services.MusicPlayerConnection;
import com.junkers.musiclink.services.MusicPlayerRemoteService;

import roboguice.inject.InjectView;

public class PlayerActivity extends BaseActivity {
    public static final String SONG_EXTRA_KEY = "song";

    @InjectView(R.id.artist_name_text_view) private TextView mArtistNameTextView;
    @InjectView(R.id.song_title_text_view) private TextView mSongTitleTextView;
    @Inject private MusicPlayerConnection mPlayerConnection;
    @Inject private Gson mGson;
    private MusicPlayerRemoteService mPlayerService;
    private Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        setupActionBar();

        song = mGson.fromJson(getIntent().getStringExtra(SONG_EXTRA_KEY), Song.class);
        displaySongInfo();
        mPlayerService = mPlayerConnection.getPlayerService();
        try {
            mPlayerService.playSong(mGson.toJson(song));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
