package com.junkers.musiclink.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junkers.musiclink.R;
import com.junkers.musiclink.app.base.BaseFragment;

public class PlayerFragment extends BaseFragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigator, container, false);
        return rootView;
    }
}
