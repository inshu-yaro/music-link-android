package com.junkers.musiclink.models;

import org.joda.time.DateTime;

/**
 * Created by kazuya on 14/07/22.
 */
public class Message {

    private String mMessage;
    private DateTime mDateTime;
    private User mUser;

    public static Message getMessageObject(String messageText, DateTime dateTime, User user){
        Message msg = new Message();
        msg.setMessage(messageText);
        msg.setDateTime(dateTime);
        msg.setUser(user);
        return msg;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public DateTime getDateTime() {
        return mDateTime;
    }

    public void setDateTime(DateTime dateTime) {
        mDateTime = dateTime;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
