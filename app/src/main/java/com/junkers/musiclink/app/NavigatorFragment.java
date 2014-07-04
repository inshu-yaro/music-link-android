package com.junkers.musiclink.app;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.adapters.QueryAdapter;
import com.junkers.musiclink.widgets.AlbumAdapter;
import com.junkers.musiclink.widgets.ArtistAdapter;
import com.junkers.musiclink.widgets.SongAdapter;

import roboguice.fragment.provided.RoboFragment;
import roboguice.inject.InjectView;

public class NavigatorFragment extends RoboFragment implements ActionBar.TabListener {
    @Inject private QueryAdapter mQueryAdapter;
    @InjectView(R.id.player_list_view) private ListView mPlayerListView;

    private int mCurrentPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigator, container, false);
        mCurrentPosition = 0;
        setupActionBar();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPlayerListView.setAdapter(getAdapter());
    }

    // TODO: handle when artist or album selected
    private ListAdapter getAdapter() {
        switch (mCurrentPosition) {
            case 0:
                return new ArtistAdapter(
                        getActivity(), R.layout.artist_row_view, mQueryAdapter.getArtists());
            case 1:
                return new AlbumAdapter(
                        getActivity(), R.layout.artist_row_view, mQueryAdapter.getAlbums());
            case 2:
                return new SongAdapter(
                        getActivity(), R.layout.artist_row_view, mQueryAdapter.getSongs());
            default:
                return null;
        }

    }

    private void setupActionBar() {
        final ActionBar actionBar = getActivity().getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText(R.string.artists_section)
                .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.albums_section)
                .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.songs_section)
                .setTabListener(this));
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        int position = tab.getPosition();
        if (mCurrentPosition == position) {
            return;
        }
        mCurrentPosition = position;
        mPlayerListView.setAdapter(getAdapter());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
