package com.junkers.musiclink.widgets;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.junkers.musiclink.R;
import com.junkers.musiclink.app.NavigatorWrapperFragment;
import com.junkers.musiclink.models.Album;

import java.util.List;

public class AlbumAdapter extends NavigatorAdapter<Album> {

    public AlbumAdapter(Context context, int resource, List<Album> objects, NavigatorWrapperFragment wrapperFragment) {
        super(context, resource, objects, wrapperFragment);
    }

    // TODO: Customize to display coverart etc
    @Override
    protected void customizeView(View view, Album album) {
        TextView albumNameView = (TextView) view.findViewById(R.id.artist_name_view);
        albumNameView.setText(album.getTitle());
    }

    @Override
    protected void onItemClick(Album album) {
        mWrapperFragment.showAlbumSongs(album);
    }
}

