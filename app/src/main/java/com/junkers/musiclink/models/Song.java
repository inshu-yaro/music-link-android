package com.junkers.musiclink.models;

public class Song {
    private String mTitle;

    public Song() {
    }

    public Song(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
