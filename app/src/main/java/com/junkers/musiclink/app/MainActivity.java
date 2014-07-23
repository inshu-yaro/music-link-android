package com.junkers.musiclink.app;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.adapters.CacheAdapter;
import com.junkers.musiclink.adapters.ChatAdapter;
import com.junkers.musiclink.app.base.BaseActivity;
import com.junkers.musiclink.models.User;
import com.junkers.musiclink.services.ChatConnection;
import com.junkers.musiclink.services.ChatService;
import com.junkers.musiclink.services.MusicPlayerConnection;
import com.junkers.musiclink.services.MusicPlayerService;

import roboguice.inject.InjectFragment;
import roboguice.inject.InjectView;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    @Inject private CacheAdapter cacheAdapter;
    @Inject private FragmentFactory mFragmentFactory;
    @Inject private MusicPlayerConnection mPlayerConnection;
    @Inject private ChatConnection mChatConnection;

    @InjectFragment(R.id.navigation_drawer) private NavigationDrawerFragment mNavigationDrawerFragment;
    @InjectView(R.id.drawer_layout) private DrawerLayout mDrawerLayout;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = cacheAdapter.loadCachedUser();

        if (user == null || !user.hasToken()) {
            launchLoginActivity();
        }

        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mPlayerConnection.isBound()) {
            Intent intent = new Intent(getApplicationContext(), MusicPlayerService.class);
            getApplicationContext().bindService(intent, mPlayerConnection, Context.BIND_AUTO_CREATE);
            getApplicationContext().startService(intent);
        }
        if(!mChatConnection.isBound()){
            Intent intent = new Intent(getApplicationContext(), ChatService.class);
            getApplicationContext().bindService(intent, mChatConnection, Context.BIND_AUTO_CREATE);
            getApplicationContext().startService(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPlayerConnection.isBound()) {
            mPlayerConnection.setBound(false);
            getApplicationContext().unbindService(mPlayerConnection);
        }
        if (mChatConnection.isBound()) {
            mChatConnection.setBound(false);
            getApplicationContext().unbindService(mChatConnection);
        }
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, LoginActivity.REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            finish();
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, mFragmentFactory.createMainFragment(position))
                .commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.player, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
