package com.trinerdis.androidmaster.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import com.trinerdis.androidmaster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Constant adapter with example items.
 */
public class HeterogeneousAdapter extends RecyclerView.Adapter<HeterogeneousAdapter.ViewHolder> {

    /**
     * Adapted item model.
     */
    public static class Item {

        public enum Type {
            SMALL,
            MEDIUM,
            LARGE
        }

        public Type type;
        public String title;
        public String description;
        public int color;

        public Item(Type type, String title, String description, int color) {
            this.type = type;
            this.title = title;
            this.description = description;
            this.color = color;
        }
    }

    /**
     * Adapted item view views holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView descriptionTextView;
        public ImageView colorImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            // Get item views.
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
     * Constructor.
     *
     * @param context Activity context.
     */
    public HeterogeneousAdapter(Context context) {
        mContext = context;

        for (int i = 0; i < 200; ++i) {
            mItems.add(new Item(
                getItemType(i),
                "Title " + i,
                "Description of item Title " + i,
                getItemColor(i)
            ));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate item layout.
        final View itemView;
        final Item.Type type = Item.Type.values()[viewType];
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (type) {
            default:
            case SMALL:
                itemView = inflater.inflate(R.layout.item_small, parent, false);
                break;
            case MEDIUM:
                itemView = inflater.inflate(R.layout.item_medium, parent, false);
                break;
            case LARGE:
                itemView = inflater.inflate(R.layout.item_large, parent, false);
                break;
        }

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
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemType(position).ordinal();
    }

    private Item.Type getItemType(int i) {
        switch (i % 3) {
            default:
            case 0:
                return Item.Type.SMALL;
            case 1:
                return Item.Type.MEDIUM;
            case 2:
                return Item.Type.LARGE;
        }
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
}
