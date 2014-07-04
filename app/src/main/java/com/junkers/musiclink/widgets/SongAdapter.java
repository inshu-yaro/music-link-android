package com.junkers.musiclink.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.models.Song;

import java.util.ArrayList;
import java.util.List;

import roboguice.RoboGuice;


// TODO: Factorize common logic
public class SongAdapter extends ArrayAdapter<Song> {
    @Inject protected LayoutInflater mLayoutInflater;
    protected int mResource;


    public SongAdapter(Context context, int resource) {
        this(context, resource, new ArrayList<Song>());
    }

    public SongAdapter(Context context, int resource, List<Song> objects) {
        super(context, resource, objects);
        mResource = resource;
        RoboGuice.injectMembers(context, this);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = mLayoutInflater.inflate(mResource, null);
        }
        Song song = getItem(position);
        TextView songNameView = (TextView) view.findViewById(R.id.artist_name_view);
        songNameView.setText(song.getTitle());
        return view;
    }
}
