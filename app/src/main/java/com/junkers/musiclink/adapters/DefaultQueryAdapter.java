package com.junkers.musiclink.adapters;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.provider.MediaStore;

import com.google.inject.Inject;
import com.junkers.musiclink.models.Album;
import com.junkers.musiclink.models.Artist;
import com.junkers.musiclink.models.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// FIXME: Album/Artist name with ' character
// TODO: Factorize duplicated code
public class DefaultQueryAdapter implements QueryAdapter {
    @Inject private Context mContext;

    @Override
    public List<Artist> getArtists() {
        Cursor cursor = getSDMusicCursor();
        Set<Artist> artists = new HashSet<Artist>();
        while (cursor.moveToNext()) {
            String artistName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST));
            artists.add(new Artist(artistName));
        }
        List<Artist> result = new ArrayList<Artist>(artists);
        Collections.sort(result);
        return result;
    }

    @Override
    public List<Album> getAlbums() {
        return getArtistAlbums(null);
    }

    @Override
    public List<Album> getArtistAlbums(Artist artist) {
        String query = "";
        if (artist != null)
            query += MediaStore.Audio.AudioColumns.ARTIST + " = '" + artist.getName() + "'";
        Cursor cursor = getSDMusicCursor(query);
        Set<Album> albums= new HashSet<Album>();
        while (cursor.moveToNext()) {
            String albumName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM));
            albums.add(new Album(albumName));
        }
        List<Album> result = new ArrayList<Album>(albums);
        Collections.sort(result);
        return result;
    }

    @Override
    public List<Song> getSongs() {
        return getSongs(null, null);
    }

    @Override
    public List<Song> getAlbumSongs(Album album) {
        return getSongs(null, album);
    }

    @Override
    public List<Song> getArtistSongs(Artist artist) {
        return getSongs(artist, null);
    }

    private List<Song> getSongs(Artist artist, Album album) {
        String query = "";
        if (artist != null)
            query += MediaStore.Audio.AudioColumns.ARTIST + " = '" + artist.getName() + "'";
        if (album != null) {
            if (artist != null)
                query += " AND ";
            query += MediaStore.Audio.AudioColumns.ALBUM + " = '" + album.getTitle() + "'";
        }
        Cursor cursor = getSDMusicCursor(query);
        List<Song> songs = new ArrayList<Song>();
        while (cursor.moveToNext()) {
            String songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE));
            songs.add(new Song(songName));
        }
        Collections.sort(songs);
        return songs;
    }

    private Cursor getSDMusicCursor() {
        return getSDMusicCursor(null);
    }

    private Cursor getSDMusicCursor(String extraQuery) {
        String query = MediaStore.Audio.AudioColumns.IS_MUSIC + " != 0";
        if (extraQuery != null && !extraQuery.isEmpty()) {
            query += " AND " + extraQuery;
        }
        CursorLoader cursorLoader = new CursorLoader(mContext);
        cursorLoader.setUri(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        cursorLoader.setSelection(query);
        cursorLoader.setProjection(new String[] {"*"});
        cursorLoader.setSortOrder(MediaStore.Audio.AudioColumns.ARTIST + " DESC");
        return cursorLoader.loadInBackground();
    }
}
