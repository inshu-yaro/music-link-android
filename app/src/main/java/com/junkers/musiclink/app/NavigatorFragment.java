package com.junkers.musiclink.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.adapters.QueryAdapter;
import com.junkers.musiclink.app.base.BaseFragment;
import com.junkers.musiclink.models.Album;
import com.junkers.musiclink.models.Artist;
import com.junkers.musiclink.models.ModelType;
import com.junkers.musiclink.models.Song;
import com.junkers.musiclink.widgets.AlbumAdapter;
import com.junkers.musiclink.widgets.ArtistAdapter;
import com.junkers.musiclink.widgets.SongAdapter;

import java.util.List;

import roboguice.inject.InjectView;

public class NavigatorFragment extends BaseFragment {
    public static final String MODEL_TYPE_KEY = "model_type";
    public static final String ALBUM_KEY = "album";
    public static final String ARTIST_KEY = "artist";

    @Inject private QueryAdapter mQueryAdapter;
    @Inject private Gson mGson;

    @InjectView(R.id.player_list_view) private ListView mPlayerListView;

    private NavigatorWrapperFragment mNavigatorWrapperFragment;
    private ModelType mModelType = ModelType.ARTIST;
    private Album mAlbum;
    private Artist mArtist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigator, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavigatorWrapperFragment = (NavigatorWrapperFragment)getFragmentManager().findFragmentById(R.id.container);
        if (getArguments() != null && getArguments().containsKey(MODEL_TYPE_KEY)) {
            mModelType = ModelType.valueOf(getArguments().getString(MODEL_TYPE_KEY));
        }
        refreshList();
    }

    private List<Album> getAlbums() {
        if (mArtist != null)
            return mQueryAdapter.getArtistAlbums(mArtist);
        else
            return mQueryAdapter.getAlbums();
    }

    private List<Song> getSongs() {
        if (mArtist != null)
            return mQueryAdapter.getArtistSongs(mArtist);
        else if (mAlbum != null)
            return mQueryAdapter.getAlbumSongs(mAlbum);
        else
            return mQueryAdapter.getSongs();
    }

    private void refreshAdapter() {
        if (mQueryAdapter != null) {
            ListAdapter adapter = getAdapter();
            mPlayerListView.setAdapter(adapter);
        }
    }

    public void refreshList() {
        mArtist = null;
        mAlbum = null;
        refreshAdapter();
    }

    public void refreshList(Artist artist) {
        mArtist = artist;
        mAlbum = null;
        refreshAdapter();
    }

    public void refreshList(Album album) {
        mAlbum = album;
        refreshAdapter();
    }

    private ArrayAdapter<?> getAdapter() {
        switch (mModelType) {
            case ARTIST:
                return new ArtistAdapter(getActivity(), R.layout.artist_row_view,
                        mQueryAdapter.getArtists(), mNavigatorWrapperFragment);
            case ALBUM:
                return new AlbumAdapter(getActivity(), R.layout.artist_row_view,
                        getAlbums(), mNavigatorWrapperFragment);
            case SONG:
                return new SongAdapter(getActivity(), R.layout.artist_row_view, getSongs());
            default: return null;
        }
    }
}
