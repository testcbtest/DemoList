package com.test.demo;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    private static MainApplication instance;

    public static Context getContext(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
