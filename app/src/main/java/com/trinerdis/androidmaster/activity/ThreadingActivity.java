package com.trinerdis.androidmaster.activity;

import android.os.Bundle;
import android.util.Log;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.trinerdis.androidmaster.R;

import java.util.Random;

public class ThreadingActivity extends AppCompatActivity {

    private static final String TAG = ThreadingActivity.class.getSimpleName();

    private class Socket {

        private String mBuffer;

        private final Object mIsFullCondition = new Object();

        public String read() throws InterruptedException {
            // Simulate network latency.
            Thread.sleep(500);

            synchronized (mIsFullCondition) {
                // Wait until there are some data in the buffer.
                mIsFullCondition.wait();

                // Peek data from buffer.
                String result = mBuffer;
                mBuffer = "";
                return result;
            }
        }

        public void write(String message) throws InterruptedException {
            // Simulate network latency.
            Thread.sleep(500);

            synchronized (mIsFullCondition) {

                // Store message to buffer.
                mBuffer = mBuffer + message;

                // Wake consumer thread.
                mIsFullCondition.notify();
            }
        }
    }

    private class ProducerThread extends Thread {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {

                    // Generate random message.
                    String message = Integer.toString(mRanomd.nextInt());

                    Log.d(TAG, "ProducerThread.run(): sending message: " + message);

                    // Send message to socket.
                    mSocket.write(message);
                }
            } catch (InterruptedException exception) {
                Log.d(TAG, "ProducerThread.run(): interrupted");
            }
        }
    }

    private class ConsumerThread extends Thread {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {

                    // Read the message using blocking call.
                    String message = mSocket.read();

                    Log.d(TAG, "ConsumerThread.run(): message: " + message);

                    // TODO Handle the message.
                    Thread.sleep(500);
                }
            } catch (InterruptedException exception) {
                Log.d(TAG, "ConsumerThread.run(): interrupted");
            }
        }
    }

    private final Socket mSocket = new Socket();

    private final Random mRanomd = new Random();

    private ProducerThread mProducerThread;
    private ConsumerThread mConsumerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_threading);

        // Override action bar.
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume()");

        super.onResume();

        mProducerThread = new ProducerThread();
        mProducerThread.start();
        mConsumerThread = new ConsumerThread();
        mConsumerThread.start();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause()");

        super.onPause();

        mProducerThread.interrupt();
        mConsumerThread.interrupt();
    }
}
