package com.example.melochizhizni;

import androidx.multidex.MultiDexApplication;

public class App extends MultiDexApplication {
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        new Prefs(this);
    }

    public static App getInstance(){return instance;}
}
