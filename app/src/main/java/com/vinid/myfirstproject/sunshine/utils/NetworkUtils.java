package com.vinid.myfirstproject.sunshine.utils;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.widget.TextView;

import com.vinid.myfirstproject.sunshine.App;
import com.vinid.myfirstproject.sunshine.BuildConfig;
import com.vinid.myfirstproject.sunshine.R;
import com.vinid.myfirstproject.sunshine.WeatherInfo;
import com.vinid.myfirstproject.sunshine.WeatherType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hungnm24 on 5/4/20
 * Copyright (c) {2020} VinID. All rights reserved.
 */

public final class NetworkUtils {

    private static final double K_TO_C = 273.5;

    public static ArrayList<WeatherInfo> jsonToWeatherList(String json) {
        ArrayList<WeatherInfo> list = new ArrayList<>();
        try {
            JSONObject jObject = new JSONObject(json);
            JSONArray weatherList = jObject.getJSONArray("list");
            for (int i = 0; i < weatherList.length(); i++) {
                JSONObject itemObj = weatherList.getJSONObject(i);
                JSONObject mainObj = itemObj.getJSONObject("main");
                String maxTemp = getFormattedTemp(mainObj.getDouble("temp_max"));
                String minTemp = getFormattedTemp(mainObj.getDouble("temp_min"));

                JSONArray weatherArr = itemObj.getJSONArray("weather");
                JSONObject weatherObj = weatherArr.getJSONObject(0);
                WeatherType weatherType = WeatherType.valueOf(weatherObj.getString("main").toUpperCase());

                String date = getFormattedDate(itemObj.getString("dt_txt"));
                WeatherInfo weatherInfo = new WeatherInfo(weatherType, date, maxTemp, minTemp);
                list.add(weatherInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void doSomething() {
        TextView textView = new TextView(App.getAppContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml("", 0));
        } else {
            textView.setText(Html.fromHtml(""));
        }
    }

    public static String getFormattedTemp(double temp) {
        return String.valueOf(Math.ceil(temp - 273));
    }

    private static String getFormattedDate(String inputDate) {
        String result = null;
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss", Locale.ENGLISH);
            Date date = inputFormat.parse(inputDate);

            DateFormat outputFormat = new SimpleDateFormat(" dd/MM", Locale.ENGLISH);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            result = getDateOfWeek(dayOfWeek) + outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getDateOfWeek(int input) {
        Context context = App.getAppContext();
        switch (input) {
            case 0:
                return context.getString(R.string.monday);
            case 1:
                return context.getString(R.string.tuesday);
            case 2:
                return context.getString(R.string.wednesday);
            case 3:
                return context.getString(R.string.thursday);
            case 4:
                return context.getString(R.string.friday);
            case 5:
                return context.getString(R.string.saturday);
            case 6:
                return context.getString(R.string.sunday);
        }
        return "";
    }
}
