package com.junkers.musiclink.adapters;

import com.junkers.musiclink.models.Album;
import com.junkers.musiclink.models.Artist;
import com.junkers.musiclink.models.Song;

import java.util.List;

public class DefaultQueryAdapter implements QueryAdapter {
    @Override
    public List<Artist> getArtists() {
        return null;
    }

    @Override
    public List<Album> getAlbums() {
        return null;
    }

    @Override
    public List<Album> getArtistAlbums(Artist artist) {
        return null;
    }

    @Override
    public List<Song> getSongs() {
        return null;
    }

    @Override
    public List<Song> getAlbumSongs(Album album) {
        return null;
    }

    @Override
    public List<Song> getArtistSongs(Artist artist) {
        return null;
    }
}
