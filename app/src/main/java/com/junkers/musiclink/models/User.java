package com.junkers.musiclink.models;

public class User {
    public static final String CACHED_KEY = "CACHED_USER";

    private String mFirstName;
    private String mLastName;
    private String mToken;

    public User() {
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public boolean hasToken() {
        return mToken != null && !mToken.isEmpty();
    }
}
