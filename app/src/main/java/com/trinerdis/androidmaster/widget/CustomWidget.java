package com.trinerdis.androidmaster.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class CustomWidget extends View {

    private static final String TAG = CustomWidget.class.getSimpleName();

    public CustomWidget(Context context) {
        super(context);

        initialize();
    }

    public CustomWidget(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize();
    }

    public CustomWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initialize();
    }

    @Override
    public void onDraw(Canvas canvas) {

        drawAnimationFrame(canvas);

        invalidate();
    }

    private void initialize() {

    }

    private void drawAnimationFrame(Canvas canvas) {
        Log.d(TAG, "drawAnimationFrame()");

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        RectF rect = new RectF(10.0f, 10.0f, getWidth() - 10.0f, getHeight() - 10.0f);
        canvas.drawRect(rect, paint);
    }
}
