package com.example.dfreeman.weatherapplicationtdd.Controller;

import com.example.dfreeman.weatherapplicationtdd.Model.PreferenceHelper;

public class Controller {
    private PreferenceHelper preferenceHelper;
    public Controller() {
        preferenceHelper = new PreferenceHelper();
    }

    public void setLocation(String location) {
        preferenceHelper.setLocation(location);
    }

    public String getLocation() {
        return preferenceHelper.getLocation();
    }

    public boolean isValid(String location) {
        return preferenceHelper.isValid(location);
    }
}
