package com.junkers.musiclink.app;

import android.app.Fragment;

public final class FragmentFactory {
    private FragmentFactory() {

    }

    public static Fragment createForPosition(int position) {
        switch (position) {
            case 1:
                return new NavigatorFragment();
            default:
                throw new IllegalArgumentException("No view for position " + position);
        }
    }
}
