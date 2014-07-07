package com.junkers.musiclink.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.inject.Inject;
import com.junkers.musiclink.app.NavigatorWrapperFragment;

import java.util.ArrayList;
import java.util.List;

import roboguice.RoboGuice;

public class NavigatorAdapter<T> extends ArrayAdapter<T> {
    @Inject protected LayoutInflater mLayoutInflater;
    protected NavigatorWrapperFragment mWrapperFragment;
    protected int mResource;

    public NavigatorAdapter(Context context, int resource) {
        this(context, resource, new ArrayList<T>());
    }

    public NavigatorAdapter(Context context, int resource, List<T> objects) {
        this(context, resource, objects, null);
    }

    public NavigatorAdapter(Context context, int resource, List<T> objects, NavigatorWrapperFragment wrapperFragment) {
        super(context, resource, objects);
        mResource = resource;
        RoboGuice.injectMembers(context, this);
        mWrapperFragment = wrapperFragment;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = mLayoutInflater.inflate(mResource, null);
        }
        T item = getItem(position);
        customizeView(view, item);
        view.setOnClickListener(new ItemClickListener(item));
        return view;
    }

    protected void customizeView(View view, T item) {

    }


    protected void onItemClick(T item) {
    }


    protected class ItemClickListener implements View.OnClickListener {
        private T mItem;

        public ItemClickListener(T item) {
            mItem = item;
        }

        @Override
        public void onClick(View v) {
            onItemClick(mItem);
        }
    }

}
