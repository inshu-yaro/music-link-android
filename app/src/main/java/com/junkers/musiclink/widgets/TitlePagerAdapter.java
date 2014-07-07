package com.junkers.musiclink.widgets;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.app.FragmentFactory;

import roboguice.inject.InjectResource;

public class TitlePagerAdapter extends FragmentPagerAdapter {
    @InjectResource(R.array.player_navigation) private String[] items;
    @Inject private FragmentFactory mFragmentFactory;

    private Bundle mBundle;


    @Inject
    public TitlePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (mBundle == null)
            mBundle = new Bundle();
        Fragment fragment = mFragmentFactory.createNavigatorFragment(position, mBundle);
        fragment.setArguments((Bundle)mBundle.clone());
        mBundle = null;
        return fragment;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return items[position];
    }

    public void setBundle(Bundle bundle) {
        mBundle = bundle;
    }
}
