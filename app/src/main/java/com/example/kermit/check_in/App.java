package com.example.kermit.check_in;

import android.app.Application;

/**
 * Created by kermit on 16/3/6.
 */
public class App extends Application{

    private static Application instance;

    public static Application getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
