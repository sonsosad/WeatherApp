package com.vinid.myfirstproject.sunshine;


import androidx.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by hungnm24 on 4/20/20
 * Copyright (c) {2020} VinID. All rights reserved.
 */

public class WeatherInfo implements Serializable {

    static final String WEATHER_TYPE_KEY = "weather_type_key";
    static final String DATE_KEY = "date_key";
    static final String MAX_TEMP_KEY = "max_temp_key";
    static final String MIN_TEMP_KEY = "min_temp_key";

    private WeatherType weatherType;
    private String date;
    private String maxTemp;
    private String minTemp;

    public WeatherInfo(WeatherType weatherType, String date, String maxTemp, String minTemp) {
        this.weatherType = weatherType;
        this.date = date;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public String getDate() {
        return date;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public Integer getSmallIconResId() {
        Integer resId = 0;
        switch (weatherType) {
            case CLEAR:
                resId = R.drawable.ic_clear;
                break;
            case CLOUDS:
                resId = R.drawable.ic_cloudy;
                break;
            case FOG:
                resId = R.drawable.ic_fog;
                break;
            case LIGHT_CLOUDS:
                resId = R.drawable.ic_light_clouds;
                break;
            case LIGHT_RAIN:
                resId = R.drawable.ic_light_rain;
                break;
            case RAIN:
                resId = R.drawable.ic_rain;
                break;
            case SNOW:
                resId = R.drawable.ic_snow;
                break;
            case STORM:
                resId = R.drawable.ic_storm;
                break;
        }
        return resId;
    }

    public Integer getBigIconResId() {
        Integer resId = 0;
        switch (weatherType) {
            case CLEAR:
                resId = R.drawable.art_clear;
                break;
            case CLOUDS:
                resId = R.drawable.art_clouds;
                break;
            case FOG:
                resId = R.drawable.art_fog;
                break;
            case LIGHT_CLOUDS:
                resId = R.drawable.art_light_clouds;
                break;
            case LIGHT_RAIN:
                resId = R.drawable.art_light_rain;
                break;
            case RAIN:
                resId = R.drawable.art_rain;
                break;
            case SNOW:
                resId = R.drawable.art_snow;
                break;
            case STORM:
                resId = R.drawable.art_storm;
                break;
        }
        return resId;
    }

    public Integer getDescriptionResId() {
        Integer resId = 0;
        switch (weatherType) {
            case CLEAR:
                resId = R.string.clear;
                break;
            case CLOUDS:
                resId = R.string.clouds;
                break;
            case FOG:
                resId = R.string.fog;
                break;
            case LIGHT_CLOUDS:
                resId = R.string.light_clouds;
                break;
            case LIGHT_RAIN:
                resId = R.string.light_rain;
                break;
            case RAIN:
                resId = R.string.rain;
                break;
            case SNOW:
                resId = R.string.snow;
                break;
            case STORM:
                resId = R.string.storm;
                break;
        }
        return resId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof WeatherInfo){
            return ((WeatherInfo) obj).weatherType == this.weatherType;
        }

        return false;
    }
}
