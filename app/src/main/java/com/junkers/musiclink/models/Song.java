package com.junkers.musiclink.models;

public class Song implements Comparable<Song> {
    private String mTitle;
    private Artist mArtist;

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

    public Artist getArtist() {
        return mArtist;
    }

    public void setArtist(Artist artist) {
        mArtist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object)this).getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (mArtist != null ? !mArtist.equals(song.mArtist) : song.mArtist != null) return false;
        if (!mTitle.equals(song.mTitle)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mTitle.hashCode();
        result = 31 * result + (mArtist != null ? mArtist.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Song another) {
        int titleCmp = normalizedTitle().compareTo(another.normalizedTitle());
        if (titleCmp != 0 || getArtist() == null || another.getArtist() == null)
            return titleCmp;
        return getArtist().compareTo(another.getArtist());
    }
}
