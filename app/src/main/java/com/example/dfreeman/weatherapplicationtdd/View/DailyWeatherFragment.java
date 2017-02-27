package com.example.dfreeman.weatherapplicationtdd.View;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dfreeman.weatherapplicationtdd.R;

public class DailyWeatherFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        View v = inflater.inflate(R.layout.activity_daily_weather_fragment, container, false);
        TextView textView = (TextView)v.findViewById(R.id.todaysWeather);
        textView.setText("weather");

        return v;
    }
}
