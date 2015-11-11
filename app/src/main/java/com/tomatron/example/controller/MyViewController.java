package com.tomatron.example.controller;

import com.tomatron.example.model.MyData;
import com.tomatron.example.service.MyDAO;
import com.tomatron.example.util.MyAPIErrorHandler;
import com.tomatron.example.util.MyScheduler;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Simple example of a 'controller' that interfaces with the API and has some business logic that
 * may eventually be presented to the view.
 */
public class MyViewController {

    //region INJECTED MEMBERS ----------------------------------------------------------------------

    private MyDAO mMyDAO;
    private MyScheduler mMyScheduler;
    private MyAPIErrorHandler mErrorHandler;

    //endregion

    //region PRIVATE MEMBERS -----------------------------------------------------------------------

    private ViewImplementer mView;

    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------

    @Inject
    public MyViewController(MyDAO dao, MyScheduler scheduler, MyAPIErrorHandler errorHandler) {
        mMyDAO = dao;
        mMyScheduler = scheduler;
        mErrorHandler = errorHandler;
    }

    //endregion

    //region LIFECYCLE METHODS ---------------------------------------------------------------------

    public void onCreated(ViewImplementer view) {
        mView = view;

        getDataFromAPI("some_id");
    }

    public void onDestroyed() {

    }

    //endregion

    //region PUBLIC METHODS ------------------------------------------------------------------------

    /**
     * Fetch data from the api.
     *
     * @param id some id for the data.
     */
    public void getDataFromAPI(String id) {
        mMyDAO.getData(id)
                .observeOn(mMyScheduler.mainThread())
                .subscribe(new DataObserver());
    }

    //endregion

    //region OBSERVERS -----------------------------------------------------------------------------

    /**
     * Observer on the 'data' result from the API.
     */
    private class EmptyDataObserver implements Observer<MyData> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            // Error occurred, handle the error.
        }

        @Override
        public void onNext(MyData myData) {
            // GET succeeded, act on it / present data to the view.
        }
    }

    /**
     * Observer on the 'data' result from the API.
     */
    private class DataObserver implements Observer<MyData> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            // Error occurred, send to the error handler.
            mErrorHandler.handle(e);
            mView.setText("Error! : " + e);
        }

        @Override
        public void onNext(MyData myData) {
            // GET succeeded, present data to the view.
            mView.setText(myData.getData());
        }
    }

    //endregion
}
