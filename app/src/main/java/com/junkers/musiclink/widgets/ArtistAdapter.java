package com.junkers.musiclink.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.app.NavigatorWrapperFragment;
import com.junkers.musiclink.models.Artist;

import java.util.ArrayList;
import java.util.List;

import roboguice.RoboGuice;

public class ArtistAdapter extends ArrayAdapter<Artist> {
    @Inject protected LayoutInflater mLayoutInflater;
    private NavigatorWrapperFragment mWrapperFragment;
    protected int mResource;


    public ArtistAdapter(Context context, int resource) {
        this(context, resource, new ArrayList<Artist>());
    }

    public ArtistAdapter(Context context, int resource, List<Artist> objects) {
        this(context, resource, objects, null);
    }

    public ArtistAdapter(Context context, int resource, List<Artist> objects, NavigatorWrapperFragment wrapperFragment) {
        super(context, resource, objects);
        mResource = resource;
        RoboGuice.injectMembers(context, this);
        mWrapperFragment = wrapperFragment;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = mLayoutInflater.inflate(mResource, null);
        }
        Artist artist = getItem(position);
        TextView artistName = (TextView) view.findViewById(R.id.artist_name_view);
        artistName.setText(artist.getName());
        view.setOnClickListener(new ItemClickListener(artist));
        return view;
    }

    private class ItemClickListener implements View.OnClickListener {
        private Artist mArtist;

        public ItemClickListener(Artist artist) {
            mArtist = artist;
        }

        @Override
        public void onClick(View v) {
            if (mWrapperFragment != null) {
                mWrapperFragment.showArtistAlbums(mArtist);
            }
        }
    }
}
