package com.trinerdis.androidmaster.api;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.gson.GsonFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

/**
 * Sample request for RoboSpice library demo.
 */
public class MessagesRequest extends GoogleHttpClientSpiceRequest<Message[]> {

    private static final String TAG = MessagesRequest.class.getSimpleName();

    private final String mUrl;

    public MessagesRequest(String url) {
        super(Message[].class);

        mUrl = url;
    }

    public String getCacheKey() {
        return "messages";
    }

    @Override
    public Message[] loadDataFromNetwork() throws Exception {
        Log.d(TAG, "loadDataFromNetwork()");

        // Create HTTP request.
        final HttpRequest request = getHttpRequestFactory()
            .buildGetRequest(new GenericUrl(mUrl));

        request.setParser(GsonFactory.getDefaultInstance()
            //.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .createJsonObjectParser());

        // Execute it to get response.
        final HttpResponse response = request.execute();

        // Parse response.
        return response.parseAs(getResultType());

        /*final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();
        final String content = response.parseAsString();
        return gson.fromJson(content, Message[].class);*/
    }
}
