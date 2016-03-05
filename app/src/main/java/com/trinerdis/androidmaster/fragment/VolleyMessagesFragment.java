package com.trinerdis.androidmaster.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import com.trinerdis.androidmaster.R;
import com.trinerdis.androidmaster.adapter.DividerItemDecoration;
import com.trinerdis.androidmaster.adapter.MessagesAdapter;
import com.trinerdis.androidmaster.api.Message;
import com.trinerdis.androidmaster.utils.GsonRequest;

import java.util.Arrays;

/**
 * API design:
 * <p>
 * https://apiary.io/
 * https://apiblueprint.org/
 * http://editor.swagger.io/
 * https://swaggergo.com
 * https://gelato.io/
 * https://readme.io/
 * https://getsandbox.com/
 * https://apigility.org/
 * <p>
 * HttpURLConnection:
 * <p>
 * http://developer.android.com/reference/java/net/HttpURLConnection.html *
 * http://developer.android.com/about/versions/marshmallow/android-6.0-changes.html
 * https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
 * <p>
 * Volley:
 * <p>
 * https://github.com/mcxiaoke/android-volley
 * http://developer.android.com/training/volley/index.html
 * compile 'com.mcxiaoke.volley:library:1.0.19'
 * <p>
 * GSON:
 * <p>
 * https://github.com/google/gson
 * compile 'com.google.code.gson:gson:2.4'
 */
public class VolleyMessagesFragment extends MessagesFragment {

    private static final String TAG = VolleyMessagesFragment.class.getSimpleName();

    /**
     * Base URL of application API.
     */
    private String mApiUrl;

    /**
     * Volley request queue.
     */
    private RequestQueue mQueue;

    /**
     * JSON deserializer.
     */
    private Gson mGson;

    public VolleyMessagesFragment() {
        // Create and configure JSON deserializer.
        mGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");

        // Create fragment layout.
        final View root = super.onCreateView(inflater, container, savedInstanceState);

        // Initialize Volley request queue.
        final Activity activity = getActivity();
        mQueue = Volley.newRequestQueue(activity);

        // Load resources.
        mApiUrl = getString(R.string.api_url);

        return root;
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume()");

        super.onResume();

        // Load/re-load messages.
        //loadMessages1();
        //loadMessages2();
    }

    /**
     * Loads list of messages from API using StringRequest of Volley library.
     */
    private void loadMessages1() {
        Log.d(TAG, "loadMessages1()");

        mQueue.add(
            new StringRequest(Request.Method.GET, mApiUrl + "messages",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "GetMessagesRequest.onResponse(): response: " + response);

                        // Deserialize response.
                        Message[] messages;
                        try {
                            messages = mGson.fromJson(response, Message[].class);
                        } catch (JsonParseException exception) {
                            Log.e(TAG, "GetMessagesRequest.onResponse(): failed to parse response: "
                                + response, exception);
                            return;
                        }

                        if (messages != null) {
                            showMessages(messages);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "GetMessagesRequest.onErrorResponse(): error: " + error);
                    }
                }
            )
        );
    }

    private void loadMessages2() {
        Log.d(TAG, "loadMessages2()");

        mQueue.add(new GsonRequest<>(Request.Method.GET, mApiUrl + "messages", Message[].class,
            new Response.Listener<Message[]>() {
                @Override
                public void onResponse(Message[] response) {
                    Log.d(TAG, "GetMessagesRequest.onResponse()");

                    if (response != null) {
                        showMessages(response);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "GetMessagesRequest.onErrorResponse(): error: " + error);
                }
            }));
    }

    private void showMessages(Message[] messages) {
        Log.d(TAG, "showMessages()");

        // Update RecyclerView adapter.
        mAdapter.clear();
        mAdapter.addAll(Arrays.asList(messages));
        mAdapter.notifyDataSetChanged();
    }
}
