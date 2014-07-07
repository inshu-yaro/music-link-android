package com.junkers.musiclink.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.models.Album;
import com.junkers.musiclink.models.Artist;
import com.junkers.musiclink.models.ModelType;
import com.junkers.musiclink.widgets.TitlePagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import roboguice.fragment.provided.RoboFragment;
import roboguice.inject.InjectView;

public class NavigatorWrapperFragment extends RoboFragment {
    @Inject private TitlePagerAdapter mTitlePagerAdapter;
    @Inject private Gson mGson;

    @InjectView(R.id.player_navigation) private TabPageIndicator mTabPageIndicator;
    @InjectView(R.id.player_pager) private ViewPager mViewPager;

    private PageChangeListener mPageChangeListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigator_wrapper, container, false);
        mPageChangeListener = new PageChangeListener();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager.setAdapter(mTitlePagerAdapter);
        mTabPageIndicator.setViewPager(mViewPager);
        mTabPageIndicator.setOnPageChangeListener(mPageChangeListener);
    }

    private NavigatorFragment getCurrentFragment() {
        return mTitlePagerAdapter.getFragment(mViewPager.getCurrentItem());
    }

    public void showArtistSongs(Artist artist) {
        mPageChangeListener.mArtist = artist;
        mViewPager.setCurrentItem(ModelType.SONG.getPosition());
    }

    public void showArtistAlbums(Artist artist) {
        mPageChangeListener.mArtist = artist;
        mViewPager.setCurrentItem(ModelType.ALBUM.getPosition());
    }

    public void showAlbumSongs(Album album) {
        mPageChangeListener.mAlbum = album;
        mViewPager.setCurrentItem(ModelType.SONG.getPosition());
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        private Album mAlbum;
        private Artist mArtist;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            NavigatorFragment fragment = mTitlePagerAdapter.getFragment(position);
            if (mAlbum != null)
                fragment.refreshList(mAlbum);
            else if (mArtist != null)
                fragment.refreshList(mArtist);
            else
                fragment.refreshList();

            mArtist = null;
            mAlbum = null;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
