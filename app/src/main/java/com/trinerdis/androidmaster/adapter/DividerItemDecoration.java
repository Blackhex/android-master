package com.trinerdis.androidmaster.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import android.support.v7.widget.RecyclerView;

/**
 * Simple recycler view decoration that add divider between items.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * Android style attributes this class is interested in.
     */
    private static final int[] ATTRIBUTES = new int[]{
        android.R.attr.listDivider
    };

    /**
     * Cached divider drawable.
     */
    private Drawable mDivider;

    /**
     * Constructor.
     *
     * @param context Activity context.
     */
    public DividerItemDecoration(Context context) {

        // Get list divider drawable from Android style.
        final TypedArray array = context.obtainStyledAttributes(ATTRIBUTES);
        mDivider = array.getDrawable(0);
        array.recycle();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }
}
