package com.ekc.hangoutsprototype;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by erickchang on 2/18/15.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private static final String TAG = MessageAdapter.class.getSimpleName();
    private ArrayList<Message> mMessageArrayList;
    private LayoutInflater mLayoutInflater;
    private ActionBarActivity mContext;
    private FragmentManager mFragmentManager;

    public MessageAdapter(ArrayList<Message> messageArrayList) {
        mMessageArrayList = messageArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = (ActionBarActivity) viewGroup.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        mFragmentManager = mContext.getSupportFragmentManager();
        View v = mLayoutInflater.inflate(R.layout.message_snippet,
                viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final ViewHolder vh = viewHolder;
        final int pos = i;
        Message message = mMessageArrayList.get(i);
        viewHolder.mFrom.setText(message.mFrom);
        if (message.mText != null) {
            viewHolder.mText.setText(message.mText);
        }
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vh.mText.setText(pos + "clicked");
                // launch chat window and add to back stack
                mFragmentManager.beginTransaction()
                        .replace(R.id.inner_container, new MessageFragment())
                        .addToBackStack(null)
                        .commit();


            }
        });
        viewHolder.mImageView.setImageResource(message.mPicResourceId);
        Log.i(TAG, "bind view height " + viewHolder.mView.getLayoutParams().height);
    }

    @Override
    public int getItemCount() {
        return mMessageArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mFrom;
        public TextView mText;
        public View mView;
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            mFrom = (TextView) v.findViewById(R.id.from);
            mText = (TextView) v.findViewById(R.id.text);
            mImageView = (ImageView) v.findViewById(R.id.person_image);
        }
    }
}
