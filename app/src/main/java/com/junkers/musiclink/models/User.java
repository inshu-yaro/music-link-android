package com.junkers.musiclink.models;

import com.facebook.model.GraphUser;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class User {
    public static final String CACHED_KEY = "CACHED_USER";

    private String mFacebookId;
    private String mFirstName;
    private String mLastName;
    private String mToken;
    private DateTime mBirthday;

    public User() {
    }

    public static User fromGraphUser(GraphUser graphUser) {
        User user = new User();
        user.setLastName(graphUser.getLastName());
        user.setFirstName(graphUser.getFirstName());
        user.setFacebookId(graphUser.getId());
        user.setBirthday(DateTime.parse(graphUser.getBirthday(), DateTimeFormat.forPattern("M/d/Y")));
        return user;
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

    public String getFacebookId() {
        return mFacebookId;
    }

    public void setFacebookId(String facebookId) {
        mFacebookId = facebookId;
    }

    public DateTime getBirthday() {
        return mBirthday;
    }

    public void setBirthday(DateTime birthday) {
        mBirthday = birthday;
    }
}
