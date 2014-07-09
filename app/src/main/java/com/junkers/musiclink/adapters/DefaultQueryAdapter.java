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
        return new ArrayList<Album>();
    }

    @Override
    public List<Album> getArtistAlbums(Artist artist) {
        return new ArrayList<Album>();
    }

    @Override
    public List<Song> getSongs() {
        return new ArrayList<Song>();
    }

    @Override
    public List<Song> getAlbumSongs(Album album) {
        return new ArrayList<Song>();
    }

    @Override
    public List<Song> getArtistSongs(Artist artist) {
        return new ArrayList<Song>();
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
