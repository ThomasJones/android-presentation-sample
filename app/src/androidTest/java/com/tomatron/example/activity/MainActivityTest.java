package com.tomatron.example.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import com.tomatron.example.controller.MyViewController;
import com.tomatron.example.util.MyScheduler;
import com.tomatron.example.util.MyToast;
import com.tomatron.example.util.RxConfirmDialog;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Integration / UI test on the main activity.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    @Mock
    private MyViewController mViewController;

    @Mock
    private RxConfirmDialog mConfirmDialog;

    @Mock
    private MyScheduler mMyScheduler;

    @Mock
    private MyToast mMyToast;


    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.initMocks(this);
    }

    @MediumTest
    public void testActivityStarts() {
        getActivity();
    }
}