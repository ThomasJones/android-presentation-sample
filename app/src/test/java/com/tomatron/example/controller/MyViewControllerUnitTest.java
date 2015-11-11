package com.tomatron.example.controller;

import com.tomatron.example.model.MyData;
import com.tomatron.example.service.MyDAO;
import com.tomatron.example.util.MyAPIErrorHandler;
import com.tomatron.example.util.MyScheduler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for my view controller.
 */
public class MyViewControllerUnitTest {

    @InjectMocks
    private MyViewController mViewController;       // Object under test

    @Mock
    private MyDAO mMockDAO;

    @Mock
    private MyScheduler mMockScheduler;

    @Mock
    private MyAPIErrorHandler mMockErrorHandler;

    @Mock
    private ViewImplementer mMockView;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Return immediate as the main thread
        when(mMockScheduler.mainThread()).thenReturn(Schedulers.immediate());
    }

    @Test
    public void testAPISuccessUpdatesUI() {

        // GIVEN the api returns some data
        MyData data = new MyData();
        data.setData("data!");
        when(mMockDAO.getData(anyString())).thenReturn(Observable.just(data));

        // WHEN the controller fetches the data on creation
        mViewController.onCreated(mMockView);

        // THEN the UI is updated with the data
        verify(mMockView).setText(data.getData());
    }

    @Test
    public void testAPIError() {

        // GIVEN the api returns an error
        Throwable exception = new RuntimeException("error!");
        Observable<MyData> errorResult = Observable.error(exception);
        when(mMockDAO.getData(anyString())).thenReturn(errorResult);

        // WHEN the controller fetches the data on creation
        mViewController.onCreated(mMockView);

        // THEN the error is delegated to the error handler
        verify(mMockErrorHandler).handle(exception);
    }

}

