package com.junkers.musiclink.models;

import java.util.List;

public class Album {
    private String mTitle;
    private List<Song> mSongs;

    public Album() {

    }

    public Album(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<Song> getSongs() {
        return mSongs;
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
    }
}
