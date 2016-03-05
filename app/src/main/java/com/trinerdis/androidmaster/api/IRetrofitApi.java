package com.trinerdis.androidmaster.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit API interface.
 */
public interface IRetrofitApi {

    @GET("messages")
    Call<Message[]> loadMessageList();

    @GET("messages/{id}")
    Call<Message> loadMessage(@Path("id") long id);

    @GET("messages")
    Call<Message[]> loadMessageListSorted(@Query("sort") String sort);

    @POST("messages")
    Call<Message> createMessage(@Body Message message);
}
