package com.tomatron.example.service;

import com.tomatron.example.model.MyData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test on the DAO methods that combine calls using Rx operators.
 */
public class RxExampleDAOTest {

    private RxExampleDAO mRxExampleDAO;

    @Mock
    private MyDAO mMockDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mRxExampleDAO = new RxExampleDAO(mMockDAO);
    }

    @Test
    public void testGetDataSimultaneously() throws Exception {

        // GIVEN two data models are returned from the API
        final MyData data0 = new MyData();
        when(mMockDAO.getData("0")).thenReturn(Observable.just(data0));
        final MyData data1 = new MyData();
        when(mMockDAO.getData("1")).thenReturn(Observable.just(data1));

        // WHEN the 'simultaneous' call is made
        List<MyData> data = mRxExampleDAO.getDataSimultaneously("0", "1")
                .toBlocking().first();

        // THEN the expected models are in the expected order
        assertEquals(data0, data.get(0));
        assertEquals(data1, data.get(1));
    }

    @Test
    public void testGetDataSequentially() throws Exception {

        // GIVEN two data models are returned from the API
        final MyData data0 = new MyData();
        when(mMockDAO.getData("0")).thenReturn(Observable.just(data0));
        final MyData data1 = new MyData();
        when(mMockDAO.getData("1")).thenReturn(Observable.just(data1));

        // WHEN the 'sequential' call is made
        List<MyData> data = mRxExampleDAO.getDataSequentially("0", "1")
                .toBlocking().first();

        // THEN the expected models are in the expected order
        assertEquals(data0, data.get(0));
        assertEquals(data1, data.get(1));
    }

    @Test
    public void testGetDataSimultaneously_Standalone() throws Exception {

        // GIVEN a DAO that returns two data models
        MyDAO mockDAO = mock(MyDAO.class);
        final MyData data0 = new MyData();
        when(mockDAO.getData("0")).thenReturn(Observable.just(data0));
        final MyData data1 = new MyData();
        when(mockDAO.getData("1")).thenReturn(Observable.just(data1));

        // WHEN the 'simultaneous' call is made
        RxExampleDAO daoUnderTest = new RxExampleDAO(mockDAO);
        List<MyData> data = daoUnderTest.getDataSimultaneously("0", "1")
                .toBlocking().first();

        // THEN the expected models are in the expected order
        assertEquals(data0, data.get(0));
        assertEquals(data1, data.get(1));
    }
}