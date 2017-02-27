package com.example.dfreeman.weatherapplicationtdd.Controller;

import android.content.Context;

import com.example.dfreeman.weatherapplicationtdd.Model.PreferenceHelper;

public class Controller {

    private PreferenceHelper preferenceHelper;

    public Controller() {
        preferenceHelper = new PreferenceHelper();
    }

    public void setLocation(Context context, String location) {
        preferenceHelper.save(context, location);
    }

    public String getLocation(Context context) {
        return preferenceHelper.getValue(context);
    }

    public boolean isValid(String location) {
        return preferenceHelper.isValid(location);
    }

}
