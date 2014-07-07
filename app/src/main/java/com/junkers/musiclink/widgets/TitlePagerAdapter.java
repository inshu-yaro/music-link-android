package com.junkers.musiclink.widgets;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.app.FragmentFactory;
import com.junkers.musiclink.app.NavigatorFragment;

import roboguice.inject.InjectResource;

public class TitlePagerAdapter extends FragmentPagerAdapter {
    @InjectResource(R.array.player_navigation) private String[] items;
    @Inject private FragmentFactory mFragmentFactory;

    private SparseArray<NavigatorFragment> mFragments;


    @Inject
    public TitlePagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new SparseArray<NavigatorFragment>();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        NavigatorFragment fragment = (NavigatorFragment) super.instantiateItem(container, position);
        mFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mFragments.removeAt(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentFactory.createNavigatorFragment(position);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return items[position];
    }

    public NavigatorFragment getFragment(int position) {
        return mFragments.get(position);
    }
}
