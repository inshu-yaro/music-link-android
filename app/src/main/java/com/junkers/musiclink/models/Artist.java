package com.junkers.musiclink.models;

import java.util.List;

public class Artist {
    private String mName;
    private List<Album> mAlbums;
    private List<Song> mSongs;

    public Artist() {
    }

    public Artist(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Album> getAlbums() {
        return mAlbums;
    }

    public void setAlbums(List<Album> albums) {
        mAlbums = albums;
    }

    public List<Song> getSongs() {
        return mSongs;
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
    }
}
