package com.example.dfreeman.weatherapplicationtdd.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dfreeman.weatherapplicationtdd.Controller.Controller;
import com.example.dfreeman.weatherapplicationtdd.R;

public class WeatherActivity extends AppCompatActivity {

    public Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controller = new Controller();
        String location = controller.getLocation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    public void onClickChangeLocation(View v) {
        controller.setLocation("0");
        Intent intent = new Intent(WeatherActivity.this, LocationActivity.class);
        startActivity(intent);
    }
}
