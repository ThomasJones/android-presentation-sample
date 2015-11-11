package com.tomatron.example;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import android.support.multidex.MultiDex;

/**
 * Silly custom runner required for multi-dex.
 */
public class TestRunner extends AndroidJUnitRunner {
    @Override
    public void onCreate(Bundle arguments) {
        MultiDex.install(getTargetContext());
        super.onCreate(arguments);
    }
}