package com.trinerdis.androidmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import com.trinerdis.androidmaster.R;
import com.trinerdis.androidmaster.activity.DynamicFragmentActivity;
import com.trinerdis.androidmaster.activity.MainActivity;
import com.trinerdis.androidmaster.activity.StaticFragmentActivity;
import com.trinerdis.androidmaster.activity.ViewModelActivity;

/**
 * Constant adapter with example items.
 */
public class ExamplesAdapter extends RecyclerView.Adapter<ExamplesAdapter.ViewHolder> {

    /**
     * Adapted item model.
     */
    public static class Item {

        public String name;
        public String description;
        public Class activityClass;

        public Item(String name, String description, Class activityClass) {
            this.name = name;
            this.description = description;
            this.activityClass = activityClass;
        }
    }

    /**
     * Adapted item view views holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            // Get item views.
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description_text_view);
        }
    }

    /**
     * Activity context.
     */
    protected final Context mContext;

    /**
     * Model items in the adapter.
     */
    private final Item[] mItems;

    /**
     * Constructor.
     *
     * @param context Activity context.
     */
    public ExamplesAdapter(Context context) {
        mContext = context;
        mItems = new Item[]{
            new Item(
                mContext.getString(R.string.example_1_name),
                mContext.getString(R.string.example_1_description),
                StaticFragmentActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_2_name),
                mContext.getString(R.string.example_2_description),
                DynamicFragmentActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_3_name),
                mContext.getString(R.string.example_3_description),
                ViewModelActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_4_name),
                mContext.getString(R.string.example_4_description),
                MainActivity.class
            )
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate item layout.
        final View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_example, parent, false);

        // Create item view holder.
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = mItems[position];

        // Set item data.
        holder.nameTextView.setText(item.name);
        holder.descriptionTextView.setText(item.description);

        // Set item on click listener.
        final int index = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View itemView) {
                onItemClick(mItems[index]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.length;
    }

    /**
     * Item on click event handler.
     *
     * @param item The clicked item.
     */
    protected void onItemClick(Item item) {
        // Show example activity.
        mContext.startActivity(new Intent(mContext, item.activityClass));
    }
}
