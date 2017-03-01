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

public class DailyWeatherFragment extends Fragment {

    private TextView cityText;
    private TextView condDescr;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;
    private TextView hum;
    private Controller controller;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_daily_weather, container, false);
        cityText = (TextView) v.findViewById(R.id.cityText);
        condDescr = (TextView) v.findViewById(R.id.condDescr);
        temp = (TextView) v.findViewById(R.id.temp);
        hum = (TextView) v.findViewById(R.id.hum);
        press = (TextView) v.findViewById(R.id.press);
        windSpeed = (TextView) v.findViewById(R.id.windSpeed);
        windDeg = (TextView) v.findViewById(R.id.windDeg);
        WeatherActivity weatherActivity = (WeatherActivity) getActivity();
        controller = weatherActivity.controller;
        String city = controller.getCachedLocation();
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(city);

        return v;
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = controller.getData(params[0]);

            try {
                weather = controller.getWeather(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
            condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
            temp.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + "�C");
            hum.setText("" + weather.currentCondition.getHumidity() + "%");
            press.setText("" + weather.currentCondition.getPressure() + " hPa");
            windSpeed.setText("" + weather.wind.getSpeed() + " mps");
            windDeg.setText("" + weather.wind.getDeg() + "�");

        }
    }
}
