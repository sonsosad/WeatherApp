package com.vinid.myfirstproject.sunshine;

import android.app.Application;
import android.content.Context;


/**
 * Created by hungnm24 on 5/6/20
 * Copyright (c) {2020} VinID. All rights reserved.
 */

public class App extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getAppContext() {
        return instance;
    }
}
