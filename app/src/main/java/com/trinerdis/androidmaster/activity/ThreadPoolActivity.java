package com.trinerdis.androidmaster.activity;

import android.os.Bundle;
import android.util.Log;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.trinerdis.androidmaster.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * http://developer.android.com/reference/java/util/concurrent/ThreadPoolExecutor.html
 */
public class ThreadPoolActivity extends AppCompatActivity {

    private static final String TAG = ThreadPoolActivity.class.getSimpleName();

    private class ComputeRunnable implements Runnable {

        private long mCount;

        public ComputeRunnable(long count) {
            mCount = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < mCount; ++i) {
                synchronized (mLock) {
                    Log.d(TAG, "run(): before: " + mTotal);

                    mTotal++;

                    Log.d(TAG, "run(): after: " + mTotal);
                }
            }
        }
    }

    protected ExecutorService mExecutor;

    protected int mTotal;
    protected long mStartTime;

    private final Object mLock = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_thread_pool);

        // Override action bar.
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        compute();
    }

    public void compute() {
        Log.d(TAG, "compute()");

        Log.d(TAG, "compute(): start");

        mStartTime = System.currentTimeMillis();

        mExecutor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; ++i) {
            mExecutor.execute(new ComputeRunnable(500));
        }
        try {
            mExecutor.shutdown();
            mExecutor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            Log.e(TAG, "compute(): failed to terminate threads", exception);
        }

        long endTime = System.currentTimeMillis();

        Log.d(TAG, "compute(): stop, time: " + (endTime - mStartTime));

        /*for (int i = 0; i < 500; ++i) {
            Collection<Future<?>> futures = new LinkedList<>();
            futures.add(mExecutor.submit(new ComputeRunnable(1000)));
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (ExecutionException exception) {
                    Log.d(TAG, "onCreate()", exception);
                } catch (InterruptedException exception) {
                    Log.d(TAG, "onCreate()", exception);
                }
            }
        }*/
    }
}
