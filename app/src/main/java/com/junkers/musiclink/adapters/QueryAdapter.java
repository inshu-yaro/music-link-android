package com.junkers.musiclink.adapters;

import com.junkers.musiclink.models.Album;
import com.junkers.musiclink.models.Artist;
import com.junkers.musiclink.models.Song;

import java.util.List;

public interface QueryAdapter {
    List<Artist> getArtists();
    List<Album> getAlbums();
    List<Album> getArtistAlbums(Artist artist);
    List<Song> getSongs();
    List<Song> getAlbumSongs(Album album);
    List<Song> getArtistSongs(Artist artist);
}
