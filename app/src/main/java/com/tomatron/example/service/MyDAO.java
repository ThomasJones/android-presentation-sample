package com.tomatron.example.service;

import com.tomatron.example.model.MyData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * My 'data-access object', an implementation of the Retrofit
 * adapter to connect to my api. (Note - not a real endpoint,
 * just an example.)
 */
@Singleton
public class MyDAO {

    private MyAPI mAPI;

    @Inject
    public MyDAO() {
        // Create the adapter that connects to the main endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient())
                .setEndpoint("https://my.api.somehost.com")
                .build();

        mAPI = restAdapter.create(MyAPI.class);
    }

    /**
     * Retrieve data from the API.
     *
     * @param id data id.
     * @return an observable on the result from the API.
     */
    public Observable<MyData> getData(String id) {
        return mAPI.getData(id);
    }
}

