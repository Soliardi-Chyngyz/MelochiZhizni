package com.example.melochizhizni;

import androidx.multidex.MultiDexApplication;
import androidx.room.Room;

import com.example.melochizhizni.data.room.Database;

public class App extends MultiDexApplication {
    public static App instance;
    public static Database database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(),
                Database.class, "my_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        new Prefs(this);
    }

    public static App getInstance(){return instance;}
}
