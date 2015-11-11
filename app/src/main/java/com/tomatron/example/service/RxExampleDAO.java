package com.tomatron.example.service;

import com.google.inject.Inject;
import com.tomatron.example.model.MyData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Simple 'data-access-object' that uses the MyDAO endpoint to combine API calls into single
 * calls using RX operators. Ordinarily these methods would just be rolled into the existing
 * DAO, but for testability, the MyDAO is injected.
 */
public class RxExampleDAO {

    private MyDAO mMyDAO;

    @Inject
    public RxExampleDAO(MyDAO myDAO) {
        mMyDAO = myDAO;
    }

    /**
     * Simple pass-through of the underlying API result.
     */
    private Observable<MyData> getData(String id) {
        return mMyDAO.getData(id);
    }

    /**
     * Retrieve two pieces of data from the API simultaneously. An example of RX zip.
     *
     * @return an observable on a list of data results that were fetched simultaneously.
     */
    public Observable<List<MyData>> getDataSimultaneously(String id0, String id1) {

        return Observable.zip(
                getData(id0),
                getData(id1),
                new Func2<MyData, MyData, List<MyData>>() {
                    @Override
                    public List<MyData> call(MyData data0, MyData data1) {
                        return Arrays.asList(data0, data1);
                    }
                });
    }

    /**
     * Retrieve two pieces of data form the API sequentially, typically used if you want to chain
     * a few calls together that depend on previous calls. An example of RX flatMap and map.

     * @return an observable on a list of data results that were fetched sequentially.
     */
    public Observable<List<MyData>> getDataSequentially(final String id0, final String id1) {

        final List<MyData> dataCollection = new ArrayList<>();

        return getData(id0)
                .flatMap(new Func1<MyData, Observable<MyData>>() {
                    @Override
                    public Observable<MyData> call(MyData data0) {
                        dataCollection.add(data0);
                        return getData(id1);
                    }
                }).map(new Func1<MyData, List<MyData>>() {
                    @Override
                    public List<MyData> call(MyData data1) {
                        dataCollection.add(data1);
                        return dataCollection;
                    }
                });
    }
}
