package com.junkers.musiclink.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.widgets.TitlePagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import roboguice.fragment.provided.RoboFragment;
import roboguice.inject.InjectView;

public class NavigatorWrapperFragment extends RoboFragment {
    @Inject private TitlePagerAdapter mTitlePagerAdapter;

    @InjectView(R.id.player_navigation) private TabPageIndicator mTabPageIndicator;
    @InjectView(R.id.player_pager) private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigator_wrapper, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager.setAdapter(mTitlePagerAdapter);
        mTabPageIndicator.setViewPager(mViewPager);
    }
}
