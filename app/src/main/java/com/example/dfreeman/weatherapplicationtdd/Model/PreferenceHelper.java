package com.example.dfreeman.weatherapplicationtdd.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceHelper {

    private static final String PREFS_NAME = "AOP_PREFS";
    private static final String PREFS_KEY = "AOP_PREFS_String";

    public PreferenceHelper() {
        super();
    }

    public void save(Context context, String text) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(PREFS_KEY, text);
        editor.apply();
    }

    public String getValue(Context context) {
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(PREFS_KEY, null);
    }

    public boolean isValid(String location) {
        return location!=null && location.length() == 5;
    }
}