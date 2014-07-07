package com.junkers.musiclink.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.viewpagerindicator.TabPageIndicator;

public class SafeTabPageIndicator extends TabPageIndicator {

    public SafeTabPageIndicator(Context context) {
        super(context);
    }

    public SafeTabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item) {
        try {
            super.setCurrentItem(item);
        } catch (IllegalStateException e) {
        }
    }
}
