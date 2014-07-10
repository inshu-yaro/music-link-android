package com.junkers.musiclink.util.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.android.LogcatAppender;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

public class DevLoggerFactory extends BaseLoggerFactory {
    protected LogcatAppender mLogcatAppender = getLogcatAppender();

    @Override
    public Logger getLogger(Class<?> clazz) {
        Logger logger = (Logger)org.slf4j.LoggerFactory.getLogger(clazz.getName());
        logger.addAppender(mLogcatAppender);
        logger.setLevel(Level.ALL);
        return logger;
    }

    private LogcatAppender getLogcatAppender() {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(mLoggerContext);
        encoder.setPattern("[%thread] %-5level - %msg%n");
        encoder.start();

        LogcatAppender appender = new LogcatAppender();
        appender.setContext(mLoggerContext);
        appender.setEncoder(encoder);
        appender.start();
        return appender;
    }
}
