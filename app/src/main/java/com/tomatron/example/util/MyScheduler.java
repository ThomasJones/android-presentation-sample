package com.tomatron.example.util;

import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Injectable, mockable scheduler for unit-testing Rx subscriptions.
 */
@Singleton
public class MyScheduler {

    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}
