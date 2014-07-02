package com.junkers.musiclink.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.junkers.musiclink.R;
import com.junkers.musiclink.models.Artist;
import com.junkers.musiclink.widgets.ArtistAdapter;

import roboguice.fragment.provided.RoboFragment;
import roboguice.inject.InjectView;

public class NavigatorFragment extends RoboFragment {
    @InjectView(R.id.player_list_view) private ListView playerListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigator, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playerListView.setAdapter(getAdapter());
    }

    private ListAdapter getAdapter() {
        ArtistAdapter artistAdapter = new ArtistAdapter(getActivity(), R.layout.artist_row_view);
        artistAdapter.add(new Artist("foo"));
        artistAdapter.add(new Artist("bar"));
        artistAdapter.add(new Artist("baz"));
        return artistAdapter;
    }
}
