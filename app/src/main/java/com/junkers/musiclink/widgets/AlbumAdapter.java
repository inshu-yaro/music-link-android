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
import com.junkers.musiclink.models.Album;

import java.util.ArrayList;
import java.util.List;

import roboguice.RoboGuice;

// TODO: Customize to display coverart etc
public class AlbumAdapter extends ArrayAdapter<Album> {
    @Inject protected LayoutInflater mLayoutInflater;
    private NavigatorWrapperFragment mWrapperFragment;
    protected int mResource;


    public AlbumAdapter(Context context, int resource) {
        this(context, resource, new ArrayList<Album>());
    }

    public AlbumAdapter(Context context, int resource, List<Album> objects) {
        this(context, resource, objects, null);
    }

    public AlbumAdapter(Context context, int resource, List<Album> objects, NavigatorWrapperFragment wrapperFragment) {
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
        Album album = getItem(position);
        TextView albumNameView = (TextView) view.findViewById(R.id.artist_name_view);
        albumNameView.setText(album.getTitle());
        view.setOnClickListener(new ItemClickListener(album));
        return view;
    }

    private class ItemClickListener implements View.OnClickListener {
        private Album mAlbum;

        public ItemClickListener(Album album) {
            mAlbum = album;
        }

        @Override
        public void onClick(View v) {
            if (mWrapperFragment != null) {
                mWrapperFragment.showAlbumSongs(mAlbum);
            }
        }
    }

}

