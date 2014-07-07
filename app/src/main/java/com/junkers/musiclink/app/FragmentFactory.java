package com.junkers.musiclink.app;

import android.app.Fragment;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.models.Album;
import com.junkers.musiclink.models.Artist;
import com.junkers.musiclink.models.ModelType;

public final class FragmentFactory {
    private @Inject Gson mGson;

    public Fragment createNavigatorFragment(int position) {
        return createNavigatorFragment(position, null, null);
    }

    public Fragment createNavigatorFragment(int position, Album album) {
        return createNavigatorFragment(position, null, album);
    }

    public Fragment createNavigatorFragment(int position, Artist artist) {
        return createNavigatorFragment(position, artist, null);
    }

    public Fragment createNavigatorFragment(int position, Artist artist, Album album) {
        Fragment fragment = new NavigatorFragment();
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
        if (artist != null) {
            arguments.putString(NavigatorFragment.ARTIST_KEY, mGson.toJson(artist));
        }

        if (album != null) {
            arguments.putString(NavigatorFragment.ALBUM_KEY, mGson.toJson(album));
        }

        fragment.setArguments(arguments);
        return fragment;
    }

    public Fragment createMainFragment(int position) {
        switch (position) {
            case 0:
                return new NavigatorWrapperFragment();
            default:
                throw new IllegalArgumentException("No view for position " + position);
        }
    }
}
