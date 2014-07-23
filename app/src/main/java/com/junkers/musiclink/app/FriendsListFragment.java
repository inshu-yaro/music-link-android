package com.junkers.musiclink.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.app.base.BaseFragment;
import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.managers.UserManager;
import com.junkers.musiclink.models.User;
import com.junkers.musiclink.widgets.FriendAdapter;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;


public class FriendsListFragment extends BaseFragment {

    @Inject private UserManager mUserManager;
    @InjectView(R.id.friends_list_view) private ListView mListView;

    private NavigatorWrapperFragment mNavigatorWrapperFragment;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapter();
    }

    private void setAdapter() {
        ListAdapter adapter = getAdapter();
        mListView.setAdapter(adapter);
    }

    private FriendAdapter getAdapter() {
        return new FriendAdapter(getActivity(), R.layout.friend_row_view, mUserManager.loadFriendsList());
    }

}
