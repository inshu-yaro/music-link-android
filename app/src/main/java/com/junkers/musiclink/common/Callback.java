package com.junkers.musiclink.common;

public interface Callback<T> {
    void onSuccess(T t);
    void onFailure();
}
