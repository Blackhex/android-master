package com.trinerdis.androidmaster.activity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.trinerdis.androidmaster.R;

/**
 * http://developer.android.com/reference/android/app/DownloadManager.html
 */
public class DownloadManagerActivity extends AppCompatActivity {

    private static final String TAG = DownloadManagerActivity.class.getSimpleName();

    /**
     * DownloadManager system service.
     */
    protected DownloadManager mManager;

    /**
     * Identifier of the started download.
     */
    protected long mDownload;

    /**
     * Broadcast receiver handling the download status change.
     */
    protected BroadcastReceiver mReceiver;

    public void onStartDownloadButtonClick(View view) {
        Log.d(TAG, "onStartDownloadButtonClick()");

        // Create and configure download request.
        Uri uri = Uri.parse("http://trinerdis.cz/bigfile.zip");
        DownloadManager.Request request = new DownloadManager.Request(uri)
            .setTitle("Stahuji...")
            .setDescription("http://trinerdis.cz/bigfile.zip")
            .setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "bigfile.zip")
            .setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI
            )
            .setVisibleInDownloadsUi(true);

        // Start downloading using DownloadManager.
        mDownload = mManager.enqueue(request);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_download_manager);

        // Override action bar.
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        // Get DownloadManager system service.
        mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        // Create BroadcastReceiver that will handle download callbacks.
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                Log.d(TAG, "Receiver.onReceive(): action:" + action);

                switch (action) {
                    case DownloadManager.ACTION_NOTIFICATION_CLICKED:
                        long[] downloads = intent.getLongArrayExtra(
                            DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS
                        );
                        for (long id : downloads) {
                            if (id == mDownload) {
                                printDownloadInfo(id);
                            }
                        }
                        break;
                    case DownloadManager.ACTION_DOWNLOAD_COMPLETE:
                        long download = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                        printDownloadInfo(download);
                        break;
                    case DownloadManager.ACTION_VIEW_DOWNLOADS:
                        break;
                }
            }
        };

        // Register it with desired intent filter actions.
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        filter.addAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy()");

        super.onDestroy();

        unregisterReceiver(mReceiver);
    }

    private void printDownloadInfo(long download) {
        // Create DownloadManager query.
        DownloadManager.Query query = new DownloadManager.Query()
            .setFilterById(download);

        // Execute it.
        Cursor cursor = mManager.query(query);


        // Get query result.
        if (cursor.moveToFirst()) {
            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            int reason = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_REASON));
            String localFilename = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));

            Log.d(TAG, "printDownloadInfo(): status: " + statusToString(status) + ", reason: "
                + reasonToString(reason) + ", localFileName: " + localFilename);
        } else {
            Log.d(TAG, "printDownloadInfo(): cursor is empty");
        }
    }

    private static String statusToString(int status) {
        switch (status) {
            case DownloadManager.STATUS_FAILED:
                return "STATUS_FAILED";
            case DownloadManager.STATUS_PAUSED:
                return "STATUS_PAUSED";
            case DownloadManager.STATUS_PENDING:
                return "STATUS_PENDING";
            case DownloadManager.STATUS_RUNNING:
                return "STATUS_RUNNING";
            case DownloadManager.STATUS_SUCCESSFUL:
                return "STATUS_SUCCESSFUL";
            default:
                return "STATUS_UNKNOWN";
        }
    }

    private static String reasonToString(int reason) {
        switch (reason) {
            case DownloadManager.ERROR_CANNOT_RESUME:
                return "ERROR_UNKNOWN";
            case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                return "ERROR_DEVICE_NOT_FOUND";
            case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                return "ERROR_FILE_ALREADY_EXISTS";
            case DownloadManager.ERROR_FILE_ERROR:
                return "ERROR_FILE_ERROR";
            case DownloadManager.ERROR_HTTP_DATA_ERROR:
                return "ERROR_HTTP_DATA_ERROR";
            case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                return "ERROR_INSUFFICIENT_SPACE";
            case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                return "ERROR_TOO_MANY_REDIRECTS";
            case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                return "ERROR_UNHANDLED_HTTP_CODE";
            case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                return "PAUSED_QUEUED_FOR_WIFI";
            case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                return "PAUSED_WAITING_FOR_NETWORK";
            case DownloadManager.PAUSED_WAITING_TO_RETRY:
                return "PAUSED_WAITING_TO_RETRY";
            default:
                return "UNKNOWN";
        }
    }
}
