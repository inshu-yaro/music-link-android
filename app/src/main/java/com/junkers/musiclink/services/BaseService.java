package com.junkers.musiclink.services;

import com.google.inject.Inject;
import com.junkers.musiclink.util.log.LoggerFactory;

import ch.qos.logback.classic.Logger;
import roboguice.service.RoboService;

public abstract class BaseService extends RoboService {
    @Inject private LoggerFactory mLoggerFactory;
    protected Logger log;

    @Override
    public void onCreate() {
        super.onCreate();
        log = mLoggerFactory.getLogger(this);
    }
}
