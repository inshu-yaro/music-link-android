package com.junkers.musiclink.models;

import java.util.List;

public class Artist implements Comparable<Artist> {
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

    protected String normalizedName() {
        return mName.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object)this).getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (!normalizedName().equals(artist.normalizedName())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return normalizedName().hashCode();
    }

    @Override
    public int compareTo(Artist another) {
        return normalizedName().compareTo(another.normalizedName());
    }
}
