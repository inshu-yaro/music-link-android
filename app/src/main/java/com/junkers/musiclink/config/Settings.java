package com.junkers.musiclink.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class Settings extends Properties {
    public void loadDefaults() {
        // TODO: set defaults values
    }

    public URI getAsUri(String key) {
        try {
            return new URI(this.getProperty(key));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
