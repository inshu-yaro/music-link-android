package com.junkers.musiclink.models;

import java.util.List;

public class Album implements Comparable<Album> {
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

    @Override
    public int compareTo(Album another) {
        return normalizedTitle().compareTo(another.normalizedTitle());
    }

    public String normalizedTitle() {
        return getTitle().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object)this).getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (!normalizedTitle().equals(album.normalizedTitle())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return normalizedTitle().hashCode();
    }
}
