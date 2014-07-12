package com.junkers.musiclink.widgets;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.app.PlayerActivity;
import com.junkers.musiclink.models.Song;

import java.util.List;


public class SongAdapter extends NavigatorAdapter<Song> {

    @Inject private Gson mGson;

    public SongAdapter(Context context, int resource, List<Song> objects) {
        super(context, resource, objects);
    }

    @Override
    protected void customizeView(View view, Song song) {
        TextView songNameView = (TextView) view.findViewById(R.id.artist_name_view);
        songNameView.setText(song.getTitle());
    }

    @Override
    protected void onItemClick(Song song) {
        Intent intent = new Intent(getContext(), PlayerActivity.class);
        intent.putExtra(PlayerActivity.SONG_EXTRA_KEY, mGson.toJson(song));
        getContext().startActivity(intent);
    }
}
