package com.tomatron.example.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import junit.framework.TestCase;

/**
 * Integration / UI test on the main activity.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    @MediumTest
    public void testActivityStarts() {
        getActivity();
    }
}