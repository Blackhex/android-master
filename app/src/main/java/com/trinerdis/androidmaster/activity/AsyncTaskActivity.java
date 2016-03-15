package com.trinerdis.androidmaster.activity;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.trinerdis.androidmaster.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * http://developer.android.com/reference/android/app/DownloadManager.html
 */
public class AsyncTaskActivity extends AppCompatActivity {

    private static final String TAG = AsyncTaskActivity.class.getSimpleName();

    private static final String EXTRA_REQUEST = "request";

    private static final int REQUEST_STOP = 1;
    private static final int REQUEST_PAUSE = 2;

    protected NotificationManager mNotificationManager;

    protected DownloadTask mDownloadTask;

    protected int mNotificationId = 1;
    protected int mTotalSize;
    protected int mCurrentSize;

    private interface OnProgressUpdateListener {
        void onProgressUpdate(int currentSize, int totalSize);
    }

    private class DownloadTask extends AsyncTask<String, Object, Void> {

        private boolean mPaused;

        private long mLastUpdate = System.currentTimeMillis();

        private final Object mLock = new Object();

        public void setPaused(boolean paused) {
            synchronized (mLock) {
                mPaused = paused;
                mLock.notify();
            }
        }

        public boolean isPaused() {
            synchronized (mLock) {
                return mPaused;
            }
        }

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute()");
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.d(TAG, "doInBackground()");

            startDownload(new OnProgressUpdateListener() {
                @Override
                public void onProgressUpdate(int currentSize, int totalSize) {
                    long now = System.currentTimeMillis();
                    if ((now - mLastUpdate) > 500) {
                        publishProgress(currentSize, totalSize);
                        mLastUpdate = now;
                    }
                }
            });

            return null;
        }

        @Override
        protected void onProgressUpdate(Object... progress) {
            Log.d(TAG, "onProgressUpdate()");

            updateNotification((int) progress[0], (int) progress[1]);
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d(TAG, "onPostExecute()");

            mDownloadTask = null;

            hideNotification();
        }

        private void startDownload(OnProgressUpdateListener listener) {
            Log.d(TAG, "startDownload()");

            final URL url;
            try {
                url = new URL("http://trinerdis.cz/bigfile.zip");
            } catch (MalformedURLException exception) {
                Log.e(TAG, "doInBackground(): invalid url", exception);
                return;
            }

            try {
                // Create and setup new connection.
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);

                // Open stream for output file.
                final File directory = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS);
                final File file = new File(directory, "bigfile.zip");
                final FileOutputStream outputStream = new FileOutputStream(file);

                // Open input stream in connection.
                connection.connect();
                final InputStream inputStream = connection.getInputStream();

                // Try to get total size of the file. This may return -1 if size is too big or unknown.
                mTotalSize = connection.getContentLength();
                mCurrentSize = 0;

                // Read through the input buffer and write the contents to the file.
                final byte[] buffer = new byte[1024];
                int bufferLength;
                while ((bufferLength = inputStream.read(buffer)) > 0) {

                    synchronized (mLock) {
                        while (mPaused) {
                            mLock.wait();
                        }
                    }

                    outputStream.write(buffer, 0, bufferLength);
                    mCurrentSize += bufferLength;

                    if (listener != null) {
                        listener.onProgressUpdate(mCurrentSize, mTotalSize);
                    }
                }

                // Close the output stream when done.
                outputStream.close();
            } catch (InterruptedIOException exception) {
                Log.d(TAG, "doInBackground(): interrupted");
            } catch(IOException exception) {
                Log.e(TAG, "doInBackground(): failed to download file", exception);
            } catch (InterruptedException e) {
                Log.d(TAG, "doInBackground(): interrupted");
            }
        }
    }

    public void onStartDownloadButtonClick(View view) {
        Log.d(TAG, "onStartDownloadButtonClick()");

        startDownload();
    }

    public void onStopDownloadButtonClick(View view) {
        Log.d(TAG, "onStopDownloadButtonClick()");

        stopDownload();
    }

    protected void startDownload() {
        Log.d(TAG, "startDownload()");

        if (mDownloadTask == null) {
            mDownloadTask = new DownloadTask();
            mDownloadTask.execute("http://trinerdis.cz/bigfile.zip");
        }
    }

    protected void pauseDownload() {
        Log.d(TAG, "pauseDownload()");

        if (mDownloadTask != null) {
            mDownloadTask.setPaused(!mDownloadTask.isPaused());
        }
    }

    protected void stopDownload() {
        Log.d(TAG, "stopDownload()");

        if (mDownloadTask != null) {
            mDownloadTask.cancel(true);
            mDownloadTask = null;
        }

        hideNotification();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_async_task);

        // Override action bar.
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        // Get NotificationManager system service.
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Request necessary permissions at run time.
        verifyStoragePermissions(this);

        // Handle intent if activity was restarted.
        Intent intent = getIntent();
        if (intent != null) {
            handleIntent(intent);
        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy()");

        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent(): action:"  + intent.getAction());

        handleIntent(intent);
    }

    private static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                1
            );
        }
    }

    private void updateNotification(int currentSize, int totalSize) {
        Log.d(TAG, "updateNotification(): currentSize: " + currentSize + ", totalSize: "
            + totalSize);

        // Create notification with downloading progress.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
            .setContentTitle("Downloading ...")
            .setContentText(getString(
                R.string.download_progress_pattern,
                Formatter.formatFileSize(this, currentSize),
                Formatter.formatFileSize(this, totalSize)
            ))
            .setSmallIcon(android.R.drawable.ic_menu_save)
            .setProgress(mTotalSize, mCurrentSize, false);

        // Add action to pause the download.
        Intent pauseIntent = new Intent(this, AsyncTaskActivity.class);
        pauseIntent.putExtra(EXTRA_REQUEST, REQUEST_PAUSE);
        pauseIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pausePendingIntent = PendingIntent.getActivity(
            this,
            REQUEST_PAUSE,
            pauseIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.addAction(android.R.drawable.ic_media_pause, "Pause", pausePendingIntent);

        // Add action to stop the download.
        Intent stopIntent = new Intent(this, AsyncTaskActivity.class);
        stopIntent.putExtra(EXTRA_REQUEST, REQUEST_STOP);
        stopIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent stopPendingIntent = PendingIntent.getActivity(
            this,
            REQUEST_STOP,
            stopIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.addAction(android.R.drawable.ic_menu_delete, "Stop", stopPendingIntent);

        // Show the notification.
        mNotificationManager.notify(mNotificationId, builder.build());
    }

    private void hideNotification() {
        Log.d(TAG, "hideNotification()");

        mNotificationManager.cancel(mNotificationId);
    }

    private void handleIntent(Intent intent) {
        Log.d(TAG, "handleIntent()");

        if (intent.hasExtra(EXTRA_REQUEST)) {
            switch (intent.getIntExtra(EXTRA_REQUEST, -1)) {
                case REQUEST_PAUSE:
                    pauseDownload();
                    break;
                case REQUEST_STOP:
                    stopDownload();
                    break;
            }
        }
    }
}
