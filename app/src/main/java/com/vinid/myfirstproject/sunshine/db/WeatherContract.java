package com.vinid.myfirstproject.sunshine.db;

import android.provider.BaseColumns;

/**
 * Created by hungnm24 on 4/26/20
 * Copyright (c) {2020} VinID. All rights reserved.
 */

public final class WeatherContract {

    private WeatherContract() {
    }

    /* Inner class that defines the table contents */
    public static class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "weather";

        public static final String COLUMN_NAME_TYPE = "weather_type";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_MAX_TEMP = "max_temp";
        public static final String COLUMN_NAME_MIN_TEMP = "min_temp";
    }
}
