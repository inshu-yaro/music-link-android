package com.junkers.musiclink.util.log;

import ch.qos.logback.classic.Logger;

public interface LoggerFactory {
    Logger getLogger(Class<?> clazz);
}
