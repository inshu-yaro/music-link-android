package com.junkers.musiclink.widgets;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.junkers.musiclink.R;
import com.junkers.musiclink.models.Message;

import java.util.List;

/**
 * Created by kazuya on 14/07/22.
 */
public class ChatMessageAdapter extends BaseAdapter<Message> {
    public ChatMessageAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
    }

    @Override
    protected void customizeView(View view, Message message) {
        TextView messageTextView = (TextView)view.findViewById(R.id.message_text_view);
        messageTextView.setText(message.getMessage());
    }

}
