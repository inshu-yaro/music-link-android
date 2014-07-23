package com.junkers.musiclink.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.models.ModelType;

public final class FragmentFactory {
    private @Inject Gson mGson;
    private @Inject Activity mActivity;


    public NavigatorFragment createNavigatorFragment(int position) {
        NavigatorFragment fragment = new NavigatorFragment();
        Bundle arguments = new Bundle();
        switch (position) {
            case 0:
                arguments.putString(NavigatorFragment.MODEL_TYPE_KEY, ModelType.ARTIST.toString());
                break;
            case 1:
                arguments.putString(NavigatorFragment.MODEL_TYPE_KEY, ModelType.ALBUM.toString());
                break;
            case 2:
                arguments.putString(NavigatorFragment.MODEL_TYPE_KEY, ModelType.SONG.toString());
                break;
            default:
                throw new IllegalArgumentException("No view for position " + position);
        }
        fragment.setArguments(arguments);

        return fragment;
    }

    public Fragment createMainFragment(int position) {
        switch (position) {
            case 0:
                return new NavigatorWrapperFragment();
            case 1:
                return new FriendsListFragment();
            default:
                throw new IllegalArgumentException("No view for position " + position);
        }
    }
}
