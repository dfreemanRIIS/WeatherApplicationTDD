package com.example.dfreeman.weatherapplicationtdd.Controller;

import android.content.Context;

import com.example.dfreeman.weatherapplicationtdd.Model.PreferenceHelper;

public class Controller {

    private PreferenceHelper preferenceHelper;
    private Context context;

    public Controller(Context context) {
        this.context = context;
        preferenceHelper = new PreferenceHelper();
    }

    public void setLocation(String location) {
        preferenceHelper.save(context, location);
    }

    public String getLocation() {
        return preferenceHelper.getValue(context);
    }

    public boolean isValid(String location) {
        return preferenceHelper.isValid(location);
    }
}
