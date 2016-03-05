package com.trinerdis.androidmaster.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trinerdis.androidmaster.api.IRetrofitApi;
import com.trinerdis.androidmaster.api.Message;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit:
 * <p/>
 * https://github.com/square/retrofit
 * http://square.github.io/retrofit/
 */
public class RetrofitMessagesFragment extends MessagesFragment {

    private static final String TAG = RetrofitMessagesFragment.class.getSimpleName();

    /**
     * Retrofit library.
     */
    private Retrofit mRetrofit;

    /**
     * Application API interface.
     */
    private IRetrofitApi mApi;

    /**
     * JSON deserializer.
     */
    private Gson mGson;

    public RetrofitMessagesFragment() {
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

        // Initialize Retrofit library.
        mRetrofit = new Retrofit.Builder()
            .baseUrl(mApiUrl)
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .build();

        // Load API interface auto-generated implementation.
        mApi = mRetrofit.create(IRetrofitApi.class);

        return root;
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume()");

        super.onResume();

        // Load/re-load messages.
        loadMessages1();
        //loadMessages2();
    }

    /**
     * Loads list of messages from API using Retrofit library synchronously in AsyncTask.
     */
    private void loadMessages1() {
        Log.d(TAG, "loadMessages1()");

        AsyncTaskCompat.executeParallel(new AsyncTask<Void, Void, Message[]>() {
            @Override
            protected Message[] doInBackground(Void... params) {
                Log.d(TAG, "LoadMessagesTask.doInBackground()");

                final Call<Message[]> request = mApi.loadMessageList();
                try {
                    final Response<Message[]> response = request.execute();
                    return response.body();
                } catch (IOException exception) {
                    Log.e(TAG, "LoadMessagesTask.doInBackground(): failed to load messages",
                        exception);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Message[] response) {
                Log.d(TAG, "LoadMessagesTask.onPostExecute()");

                if (response != null) {
                    showMessages(response);
                }
            }
        });
    }

    private void loadMessages2() {
        Log.d(TAG, "loadMessages2()");

        mApi.loadMessageList()
            .enqueue(new Callback<Message[]>() {
                @Override
                public void onResponse(Call<Message[]> call, Response<Message[]> response) {
                    Log.d(TAG, "LoadMessagesCallback.onResponse()");

                    if (response.body() != null) {
                        showMessages(response.body());
                    }
                }

                @Override
                public void onFailure(Call<Message[]> call, Throwable throwable) {
                    Log.e(TAG, "LoadMessagesCallback.onFailure(): failed to load messages", throwable);
                }
            });
    }
}
