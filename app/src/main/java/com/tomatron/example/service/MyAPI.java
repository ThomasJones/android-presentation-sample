package com.tomatron.example.service;

import com.tomatron.example.model.MyData;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Retrofit API interface into my api. (Note - this isn't a real endpoint, just an example.)
 */
public interface MyAPI {

    @GET("/myapi/{id}/data")
    Observable<MyData> getData(@Path("id") String id);
}

