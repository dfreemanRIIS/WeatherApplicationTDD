package com.example.dfreeman.weatherapplicationtdd.View;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dfreeman.weatherapplicationtdd.Controller.Controller;
import com.example.dfreeman.weatherapplicationtdd.Model.Weather;
import com.example.dfreeman.weatherapplicationtdd.R;

import org.json.JSONException;

public class WeeklyWeatherFragment extends Fragment {

    private TextView cityTextFirstDay;
    private TextView condDescrFirstDay;
    private TextView tempFirstDay;
    private TextView cityTextSecondDay;
    private TextView condDescrSecondDay;
    private TextView tempSecondDay;
    private TextView cityTextThirdDay;
    private TextView condDescrThirdDay;
    private TextView tempThirdDay;
    private TextView cityTextFourthDay;
    private TextView condDescrFourthDay;
    private TextView tempFourthDay;
    private TextView cityTextFifthDay;
    private TextView condDescrFifthDay;
    private TextView tempFifthDay;
    private TextView cityTextWeekly;
    private Controller controller;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_weekly_weather, container, false);
        cityTextFirstDay = (TextView) v.findViewById(R.id.cityTextFirstDay);
        condDescrFirstDay = (TextView) v.findViewById(R.id.condDescrFirstDay);
        tempFirstDay = (TextView) v.findViewById(R.id.tempFirstDay);
        cityTextSecondDay = (TextView) v.findViewById(R.id.cityTextSecondDay);
        condDescrSecondDay = (TextView) v.findViewById(R.id.condDescrSecondDay);
        tempSecondDay = (TextView) v.findViewById(R.id.tempSecondDay);
        cityTextThirdDay = (TextView) v.findViewById(R.id.cityTextThirdDay);
        condDescrThirdDay = (TextView) v.findViewById(R.id.condDescrThirdDay);
        tempThirdDay = (TextView) v.findViewById(R.id.tempThirdDay);
        cityTextFourthDay = (TextView) v.findViewById(R.id.cityTextFourthDay);
        condDescrFourthDay = (TextView) v.findViewById(R.id.condDescrFourthDay);
        tempFourthDay = (TextView) v.findViewById(R.id.tempFourthDay);
        cityTextFifthDay = (TextView) v.findViewById(R.id.cityTextFifthDay);
        condDescrFifthDay = (TextView) v.findViewById(R.id.condDescrFifthDay);
        tempFifthDay = (TextView) v.findViewById(R.id.tempFifthDay);
        cityTextWeekly = (TextView) v.findViewById(R.id.cityTextWeekly);
        WeatherActivity weatherActivity = (WeatherActivity) getActivity();
        controller = weatherActivity.controller;
        String city = controller.getCachedLocation();
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(city);

        return v;
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather[]> {

        @Override
        protected Weather[] doInBackground(String... params) {
            Weather[] weather = new Weather[5];
            String data = controller.getWeeklyData(params[0]);

            try {
                weather = controller.getWeeklyWeather(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;
        }

        @Override
        protected void onPostExecute(Weather[] weather) {
            super.onPostExecute(weather);

            cityTextWeekly.setText(controller.getCachedLocation());
            cityTextFirstDay.setText(R.string.today);
            condDescrFirstDay.setText(weather[0].currentCondition.getCondition() + "(" + weather[0].currentCondition.getDescr() + ")");
            tempFirstDay.setText("" + Math.round((weather[0].temperature.getTemp() - 273.15)) + "�C");
            cityTextSecondDay.setText(R.string.tomorrow);
            condDescrSecondDay.setText(weather[1].currentCondition.getCondition() + "(" + weather[1].currentCondition.getDescr() + ")");
            tempSecondDay.setText("" + Math.round((weather[1].temperature.getTemp() - 273.15)) + "�C");
            cityTextThirdDay.setText(R.string.threeDay);
            condDescrThirdDay.setText(weather[2].currentCondition.getCondition() + "(" + weather[2].currentCondition.getDescr() + ")");
            tempThirdDay.setText("" + Math.round((weather[2].temperature.getTemp() - 273.15)) + "�C");
            cityTextFourthDay.setText(R.string.fourDay);
            condDescrFourthDay.setText(weather[3].currentCondition.getCondition() + "(" + weather[3].currentCondition.getDescr() + ")");
            tempFourthDay.setText("" + Math.round((weather[3].temperature.getTemp() - 273.15)) + "�C");
            cityTextFifthDay.setText(R.string.fiveDay);
            condDescrFifthDay.setText(weather[4].currentCondition.getCondition() + "(" + weather[4].currentCondition.getDescr() + ")");
            tempFifthDay.setText("" + Math.round((weather[4].temperature.getTemp() - 273.15)) + "�C");
        }
    }
}