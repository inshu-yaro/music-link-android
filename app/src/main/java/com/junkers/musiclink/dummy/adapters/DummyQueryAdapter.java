package com.junkers.musiclink.dummy.adapters;

import com.junkers.musiclink.adapters.QueryAdapter;
import com.junkers.musiclink.models.Album;
import com.junkers.musiclink.models.Artist;
import com.junkers.musiclink.models.Song;

import java.util.Arrays;
import java.util.List;

public class DummyQueryAdapter implements QueryAdapter {
    private List<Song> songsList = Arrays.asList(
            new Song("abc"), new Song("def"), new Song("ghi"),
            new Song("jkl"), new Song("mno"), new Song("pqr"),
            new Song("stu"), new Song("vwx"), new Song("yz0"),
            new Song("123"), new Song("456"), new Song("789")
    );

    private List<Album> albumsList = Arrays.asList(
            new Album("stu"), new Album("vwx"), new Album("yz0"),
            new Album("123"), new Album("456"), new Album("789"),
            new Album("abc"), new Album("def"), new Album("ghi"),
            new Album("jkl"), new Album("mno"), new Album("pqr")
    );

    @Override
    public List<Artist> getArtists() {
        return Arrays.asList(
                new Artist("123"), new Artist("456"), new Artist("789"),
                new Artist("jkl"), new Artist("mno"), new Artist("pqr"),
                new Artist("stu"), new Artist("vwx"), new Artist("yz0"),
                new Artist("abc"), new Artist("def"), new Artist("ghi")
        );
    }

    @Override
    public List<Album> getAlbums() {
        return albumsList;
    }

    @Override
    public List<Album> getArtistAlbums(Artist artist) {
        return albumsList;
    }

    @Override
    public List<Song> getSongs() {
        return songsList;
    }

    @Override
    public List<Song> getAlbumSongs(Album album) {
        return songsList;
    }

    @Override
    public List<Song> getArtistSongs(Artist artist) {
        return songsList;
    }
}
