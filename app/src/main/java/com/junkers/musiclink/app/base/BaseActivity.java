package com.junkers.musiclink.app.base;

import android.os.Bundle;

import com.google.inject.Inject;
import com.junkers.musiclink.util.log.LoggerFactory;

import ch.qos.logback.classic.Logger;
import roboguice.activity.RoboActivity;

public class BaseActivity extends RoboActivity {
    @Inject private LoggerFactory mLoggerFactory;
    protected Logger log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log = mLoggerFactory.getLogger(((Object)this).getClass());
    }
}
