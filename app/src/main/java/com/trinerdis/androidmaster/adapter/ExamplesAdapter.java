package com.trinerdis.androidmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import com.trinerdis.androidmaster.R;
import com.trinerdis.androidmaster.activity.AsyncTaskActivity;
import com.trinerdis.androidmaster.activity.DownloadManagerActivity;
import com.trinerdis.androidmaster.activity.DynamicFragmentActivity;
import com.trinerdis.androidmaster.activity.MaterialDesignActivity;
import com.trinerdis.androidmaster.activity.RecyclerViewActivity;
import com.trinerdis.androidmaster.activity.RenderingActivity;
import com.trinerdis.androidmaster.activity.RetrofitActivity;
import com.trinerdis.androidmaster.activity.RoboSpiceActivity;
import com.trinerdis.androidmaster.activity.StaticFragmentActivity;
import com.trinerdis.androidmaster.activity.TabsActivity;
import com.trinerdis.androidmaster.activity.ThreadPoolActivity;
import com.trinerdis.androidmaster.activity.ThreadingActivity;
import com.trinerdis.androidmaster.activity.ViewModelActivity;
import com.trinerdis.androidmaster.activity.VolleyActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private final List<Item> mItems;

    /**
     * Constructor.
     *
     * @param context Activity context.
     */
    public ExamplesAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>(Arrays.asList(new Item[] {
            new Item(
                mContext.getString(R.string.example_1_name),
                mContext.getString(R.string.example_1_description),
                RecyclerViewActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_2_name),
                mContext.getString(R.string.example_2_description),
                StaticFragmentActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_3_name),
                mContext.getString(R.string.example_3_description),
                DynamicFragmentActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_4_name),
                mContext.getString(R.string.example_4_description),
                ViewModelActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_5_name),
                mContext.getString(R.string.example_5_description),
                TabsActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_6_name),
                mContext.getString(R.string.example_6_description),
                MaterialDesignActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_7_name),
                mContext.getString(R.string.example_7_description),
                VolleyActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_8_name),
                mContext.getString(R.string.example_8_description),
                RetrofitActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_9_name),
                mContext.getString(R.string.example_9_description),
                RoboSpiceActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_10_name),
                mContext.getString(R.string.example_10_description),
                DownloadManagerActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_11_name),
                mContext.getString(R.string.example_11_description),
                AsyncTaskActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_12_name),
                mContext.getString(R.string.example_12_description),
                ThreadingActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_13_name),
                mContext.getString(R.string.example_13_description),
                ThreadPoolActivity.class
            ),
            new Item(
                mContext.getString(R.string.example_14_name),
                mContext.getString(R.string.example_14_description),
                RenderingActivity.class
            )
        }));

        // Reverse examples to chronological order.
        Collections.reverse(mItems);
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
        final Item item = mItems.get(position);

        // Set item data.
        holder.nameTextView.setText(item.name);
        holder.descriptionTextView.setText(item.description);

        // Set item on click listener.
        final int index = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View itemView) {
                onItemClick(mItems.get(index));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
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
