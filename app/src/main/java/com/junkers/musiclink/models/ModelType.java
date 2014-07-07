package com.junkers.musiclink.models;

public enum ModelType {
    ARTIST(0),
    ALBUM(1),
    SONG(2);

    private int mPosition;

    ModelType(int position) {
        mPosition = position;
    }

    public int getPosition() {
        return mPosition;
    }
}
