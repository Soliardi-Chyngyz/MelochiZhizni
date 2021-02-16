package com.example.melochizhizni;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;
    public static Prefs instance;

    public Prefs (Context context) {
        instance = this;
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveShowState() {
        preferences.edit().putBoolean("showState", true).apply();
    }

    public boolean getShowState () {
        return preferences.getBoolean("showState", false);
    }

    public void saveNumber(String number) {
        preferences.edit().putString("number", number).apply();
    }

    public String getNumber() {
        return preferences.getString("number", null);
    }

    public void saveImage(String image) {
        preferences.edit().putString("image", image).apply();
    }

    public String getImage() {
        return preferences.getString("image", null);
    }

    public void saveName(String name) {
        preferences.edit().putString("name", name).apply();
    }

    public String getName() {
        return preferences.getString("name", null);
    }
}
