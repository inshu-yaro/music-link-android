package com.junkers.musiclink.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.app.base.BaseFragment;
import com.junkers.musiclink.services.MusicPlayerConnection;
import com.junkers.musiclink.services.MusicPlayerService;

import roboguice.inject.InjectView;

public class PlayerFragment extends BaseFragment {
    @InjectView(R.id.toggle_pause_button) private ImageButton mTogglePauseButton;
    @Inject private MusicPlayerConnection mPlayerConnection;

    private MusicPlayerService mPlayerService;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_full, container, false);
        mPlayerService = mPlayerConnection.getPlayerService();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTogglePauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayerService.togglePause();
            }
        });
    }
}
