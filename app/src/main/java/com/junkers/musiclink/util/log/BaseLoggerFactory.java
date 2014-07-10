package com.junkers.musiclink.util.log;

import ch.qos.logback.classic.LoggerContext;

public abstract class BaseLoggerFactory implements LoggerFactory {
    protected final LoggerContext mLoggerContext = getLoggerContext();

    private LoggerContext getLoggerContext() {
        LoggerContext lc = (LoggerContext)org.slf4j.LoggerFactory.getILoggerFactory();
        lc.reset();
        return lc;
    }

}
