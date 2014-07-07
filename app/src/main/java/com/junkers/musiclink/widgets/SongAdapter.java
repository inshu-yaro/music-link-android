package com.junkers.musiclink.widgets;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.junkers.musiclink.R;
import com.junkers.musiclink.models.Song;

import java.util.List;


public class SongAdapter extends NavigatorAdapter<Song> {

    public SongAdapter(Context context, int resource, List<Song> objects) {
        super(context, resource, objects);
    }

    @Override
    protected void customizeView(View view, Song song) {
        TextView songNameView = (TextView) view.findViewById(R.id.artist_name_view);
        songNameView.setText(song.getTitle());
    }

}
