package com.trinerdis.androidmaster.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.trinerdis.androidmaster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Constant adapter with example items.
 */
public class AnimatedAdapter extends RecyclerView.Adapter<AnimatedAdapter.ViewHolder> {

    /**
     * Adapted item model.
     */
    public static class Item {

        public String title;
        public String description;
        public int color;

        public Item(String title, String description, int color) {
            this.title = title;
            this.description = description;
            this.color = color;
        }
    }

    /**
     * Adapted item view views holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public TextView titleTextView;
        public TextView descriptionTextView;
        public ImageView colorImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            // Get item views.
            this.itemView = itemView;
            titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description_text_view);
            colorImageView = (ImageView) itemView.findViewById(R.id.color_image_view);
        }
    }

    /**
     * Activity context.
     */
    protected final Context mContext;

    /**
     * Model items in the adapter.
     */
    private final List<Item> mItems = new ArrayList<>();

    /**
     * Last animated item position.
     */
    private int mLastPosition = -1;


    /**
     * Constructor.
     *
     * @param context Activity context.
     */
    public AnimatedAdapter(Context context) {
        mContext = context;

        for (int i = 0; i < 200; ++i) {
            mItems.add(new Item(
                "Title " + i,
                "Description of item Title " + i,
                getItemColor(i)
            ));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate item layout.
        final View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_small, parent, false);

        // Create item view holder.
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = mItems.get(position);

        // Set item data.
        holder.titleTextView.setText(item.title);
        holder.descriptionTextView.setText(item.description);
        holder.colorImageView.setImageDrawable(new ColorDrawable(item.color));

        // Start animation when item appears.
        startAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private int getItemColor(int i) {
        switch (i % 2) {
            default:
            case 0:
                return ContextCompat.getColor(mContext, R.color.accent);
            case 1:
                return ContextCompat.getColor(mContext, android.R.color.transparent);
        }
    }

    private void startAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated.
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            mLastPosition = position;
        }
    }
}
