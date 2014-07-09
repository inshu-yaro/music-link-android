package com.junkers.musiclink.models;

public class Song implements Comparable<Song> {
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

    public String normalizedTitle() {
        return mTitle.toLowerCase();
    }

    @Override
    public int compareTo(Song another) {
        return normalizedTitle().compareTo(another.normalizedTitle());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object)this).getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (!normalizedTitle().equals(song.normalizedTitle())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return normalizedTitle().hashCode();
    }
}
