package com.trinerdis.androidmaster.utils;

import java.io.UnsupportedEncodingException;

import android.util.Log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * VolleyWrapper adapter for JSON requests that will be parsed into Java objects by Gson.
 */
public class GsonRequest<TResponse> extends Request<TResponse> {

  /* Public Methods *******************************************************************************/

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param method        Request method.
     * @param url           URL of the request to make
     * @param request       Request object.
     * @param responseClass Relevant response class object, for Gson's reflection.
     * @param listener      Successful response handler.
     * @param errorListener Error response handler.
     */
    public GsonRequest(
        int method,
        @NonNull String url,
        @NonNull Object request,
        @NonNull Class<TResponse> responseClass,
        @NonNull Listener<TResponse> listener,
        @Nullable ErrorListener errorListener
    ) {
        super(method, url, errorListener);

        // Initialize attributes.
        mRequest = request;
        mResponseClass = responseClass;
        mListener = listener;
    }

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param method        Request method.
     * @param url           URL of the request to make
     * @param responseClass Relevant response class object, for Gson's reflection.
     * @param listener      Successful response handler.
     * @param errorListener Error response handler.
     */
    public GsonRequest(
        int method,
        @NonNull String url,
        @NonNull Class<TResponse> responseClass,
        @NonNull Listener<TResponse> listener,
        @Nullable ErrorListener errorListener
    ) {
        super(method, url, errorListener);

        // Initialize attributes.
        mRequest = null;
        mResponseClass = responseClass;
        mListener = listener;
    }

    /**
     * @see Request#getBodyContentType()
     */
    @Override
    @NonNull
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    /**
     * @see Request#getBody()
     */
    @Override
    @Nullable
    public byte[] getBody() {
        String json = GSON.toJson(mRequest);

        Log.v(TAG, "getBody(): url: " + getUrl() + ", request: " + json);

        try {
            return json.getBytes("UTF-8");
        } catch (UnsupportedEncodingException exception) {
            Log.e(TAG, "getBody(): unsupported encoding", exception);

            return null;
        }
    }

  /* Protected Methods ****************************************************************************/

    /**
     * @see Request#deliverResponse(Object)
     */
    @Override
    protected void deliverResponse(@Nullable TResponse response) {
        mListener.onResponse(response);
    }

    /**
     * @see Request#parseNetworkResponse(NetworkResponse)
     */
    @Override
    @NonNull
    protected Response<TResponse> parseNetworkResponse(@NonNull NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            Log.v(TAG, "parseNetworkResponse(): response: " + json);

            return Response.success(GSON.fromJson(json, mResponseClass),
                HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException exception) {
            return Response.error(new ParseError(exception));
        } catch (JsonSyntaxException exception) {
            return Response.error(new ParseError(exception));
        }
    }

  /* Private Constants ****************************************************************************/

    /**
     * Debug log tag.
     */
    private static final String TAG = GsonRequest.class.getSimpleName();

    /**
     * GSON JSON parser.
     */
    private static final Gson GSON = new GsonBuilder()
        .serializeNulls()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .create();

  /* Private Attributes ***************************************************************************/

    /**
     * Request object.
     */
    private final Object mRequest;

    /**
     * Response object class.
     */
    private final Class<TResponse> mResponseClass;

    /**
     * Response listener.
     */
    private final Listener<TResponse> mListener;
}
