package com.trinerdis.androidmaster.adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import com.trinerdis.androidmaster.R;

/**
 * Constant adapter with example items.
 */
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    /**
     * Message model.
     */
    public static class Message {

        public Date date;
        public String name;
        public String text;
    }

    /**
     * Adapted item view views holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dateTextView;
        public TextView nameTextView;
        public TextView textTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            // Get item views.
            dateTextView = (TextView) itemView.findViewById(R.id.date_text_view);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            textTextView = (TextView) itemView.findViewById(R.id.text_text_view);
        }
    }

    /**
     * Activity context.
     */
    protected final Context mContext;

    /**
     * Model items in the adapter.
     */
    private final List<Message> mItems = new ArrayList<>();

    /**
     * Date formatter.
     */
    private final DateFormat mDateFormat = SimpleDateFormat.getDateTimeInstance();

    /**
     * Constructor.
     *
     * @param context Activity context.
     */
    public MessagesAdapter(Context context) {
        mContext = context;

        Message item = new Message();
        item.date = new Date();
        item.name = "Test";
        item.text = "Test message";
        mItems.add(item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate item layout.
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View itemView = inflater.inflate(R.layout.item_message, parent, false);

        // Create item view holder.
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Message item = mItems.get(position);

        // Set item data.
        holder.dateTextView.setText(mDateFormat.format(item.date));
        holder.nameTextView.setText(item.name + ":");
        holder.textTextView.setText(item.text);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
