package com.junkers.musiclink.app.base;

import android.os.Bundle;

import com.google.inject.Inject;
import com.junkers.musiclink.util.log.LoggerFactory;

import ch.qos.logback.classic.Logger;
import roboguice.fragment.provided.RoboFragment;

public class BaseFragment extends RoboFragment {
    @Inject private LoggerFactory mLoggerFactory;
    protected Logger log;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log = mLoggerFactory.getLogger(getClass());
    }
}
