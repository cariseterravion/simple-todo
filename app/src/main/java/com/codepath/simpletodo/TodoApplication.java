package com.codepath.simpletodo;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by carise on 7/31/16.
 */
public class TodoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
