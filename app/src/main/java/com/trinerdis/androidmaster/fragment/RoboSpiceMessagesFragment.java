package com.trinerdis.androidmaster.fragment;

import android.util.Log;

import com.octo.android.robospice.GsonGoogleHttpClientSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import com.trinerdis.androidmaster.api.Message;
import com.trinerdis.androidmaster.api.MessagesRequest;

/**
 * RoboSpice:
 * <p/>
 * https://github.com/stephanenicolas/robospice
 * https://github.com/octo-online/RoboSpice-sample
 * compile 'com.octo.android.robospice:robospice:1.4.5'
 * https://github.com/stephanenicolas/robospice/wiki/Starter-guide
 * UI Space List
 * <p/>
 * Google HTTP Client:
 * <p/>
 * https://github.com/google/google-http-java-client
 */
public class RoboSpiceMessagesFragment extends MessagesFragment {

    private static final String TAG = RoboSpiceMessagesFragment.class.getSimpleName();

    /**
     * RoboSpice service manager.
     */
    private final SpiceManager mSpiceManager = new SpiceManager(GsonGoogleHttpClientSpiceService.class);

    @Override
    public void onStart() {
        Log.d(TAG, "onStart()");

        super.onStart();

        mSpiceManager.start(getActivity());
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume()");

        super.onResume();

        // Load/re-load messages.
        loadMessages();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop()");

        mSpiceManager.shouldStop();

        super.onStop();
    }

    /**
     * Loads list of messages from API using StringRequest of Volley library.
     */
    private void loadMessages() {
        Log.d(TAG, "loadMessages()");

        final MessagesRequest request = new MessagesRequest(getMessagesUrl());
        mSpiceManager.execute(
            request,
            request.getCacheKey(),
            DurationInMillis.ALWAYS_EXPIRED,
            new RequestListener<Message[]>() {
                @Override
                public void onRequestSuccess(Message[] messages) {
                    Log.d(TAG, "MessagesRequestListener.onRequestSuccess()");

                    if (messages != null) {
                        showMessages(messages);
                    }
                }

                @Override
                public void onRequestFailure(SpiceException exception) {
                    Log.e(TAG, "MessagesRequestListener.onRequestFailure(): failed to load messages",
                        exception);
                }
            }
        );
    }
}
