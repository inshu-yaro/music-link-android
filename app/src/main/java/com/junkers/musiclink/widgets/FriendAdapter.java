package com.junkers.musiclink.widgets;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.app.ChatActivity;
import com.junkers.musiclink.app.PlayerActivity;
import com.junkers.musiclink.models.Message;
import com.junkers.musiclink.models.Song;
import com.junkers.musiclink.models.User;

import java.util.List;

/**
 * Created by kazuya on 14/07/22.
 */
public class FriendAdapter extends BaseAdapter<User> {

    @Inject
    private Gson mGson;

    public FriendAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
    }

    @Override
    protected void customizeView(View view, User user) {
        TextView messageTextView = (TextView)view.findViewById(R.id.friend_name_view);
        messageTextView.setText(user.getFirstName());
    }

    @Override
    protected void onItemClick(User user) {
        /*Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.putExtra(ChatActivity.USER_EXTRA_KEY, mGson.toJson(user));
        getContext().startActivity(intent);*/
    }

}
