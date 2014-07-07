package com.junkers.musiclink.widgets;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.junkers.musiclink.R;
import com.junkers.musiclink.app.NavigatorWrapperFragment;
import com.junkers.musiclink.models.Artist;

import java.util.List;

public class ArtistAdapter extends NavigatorAdapter<Artist> {

    public ArtistAdapter(Context context, int resource, List<Artist> objects, NavigatorWrapperFragment wrapperFragment) {
        super(context, resource, objects, wrapperFragment);
    }

    @Override
    protected void customizeView(View view, Artist artist) {
        TextView artistName = (TextView) view.findViewById(R.id.artist_name_view);
        artistName.setText(artist.getName());
    }

    @Override
    protected void onItemClick(Artist artist) {
        mWrapperFragment.showArtistAlbums(artist);
    }
}
