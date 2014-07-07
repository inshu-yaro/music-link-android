package com.junkers.musiclink.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.adapters.QueryAdapter;
import com.junkers.musiclink.models.Album;
import com.junkers.musiclink.models.Artist;
import com.junkers.musiclink.models.ModelType;
import com.junkers.musiclink.widgets.AlbumAdapter;
import com.junkers.musiclink.widgets.ArtistAdapter;
import com.junkers.musiclink.widgets.SongAdapter;

import roboguice.fragment.provided.RoboFragment;
import roboguice.inject.InjectView;

public class NavigatorFragment extends RoboFragment {
    public static final String MODEL_TYPE_KEY = "model_type";
    public static final String ALBUM_KEY = "album";
    public static final String ARTIST_KEY = "artist";

    @Inject private QueryAdapter mQueryAdapter;
    @Inject private Gson mGson;

    @InjectView(R.id.player_list_view) private ListView mPlayerListView;


    private ModelType mModelType = ModelType.ARTIST;
    private Album mAlbum;
    private Artist mArtist;

    private void setUpModel() {
        if (getArguments() == null) {
            return;
        }
        mModelType = ModelType.valueOf(getArguments().getString(MODEL_TYPE_KEY, ModelType.ARTIST.toString()));
        if (getArguments().containsKey(ALBUM_KEY)) {
            mAlbum = mGson.fromJson(getArguments().getString(ALBUM_KEY), Album.class);
        }
        if (getArguments().containsKey(ARTIST_KEY)) {
            mArtist = mGson.fromJson(getArguments().getString(ARTIST_KEY), Artist.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigator, container, false);
        setUpModel();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPlayerListView.setAdapter(getAdapter());
    }

    // TODO: handle when artist or album selected
    private ListAdapter getAdapter() {
        switch (mModelType) {
            case ARTIST:
                return new ArtistAdapter(
                        getActivity(), R.layout.artist_row_view, mQueryAdapter.getArtists());
            case ALBUM:
                return new AlbumAdapter(
                        getActivity(), R.layout.artist_row_view, mQueryAdapter.getAlbums());
            case SONG:
                return new SongAdapter(
                        getActivity(), R.layout.artist_row_view, mQueryAdapter.getSongs());
            default: return null;
        }
    }

}
