package com.example.dfreeman.weatherapplicationtdd.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dfreeman.weatherapplicationtdd.Controller.Controller;
import com.example.dfreeman.weatherapplicationtdd.R;

public class WeatherActivity extends AppCompatActivity {

    public Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controller = new Controller(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        TextView textView = (TextView)findViewById(R.id.currentLocation);
        textView.setText(controller.getLocation());
    }

    public void onClickChangeLocation(View v) {
        controller.setLocation("noLocation");
        Intent intent = new Intent(WeatherActivity.this, LocationActivity.class);
        startActivity(intent);
    }
}
