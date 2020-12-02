package com.vinid.myfirstproject.sunshine.api;

import com.vinid.myfirstproject.sunshine.models.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hungnm24 on 5/11/20
 * Copyright (c) {2020} VinID. All rights reserved.
 */

public interface WeatherService {
    @GET("data/{path}/forecast")
    Call<Data> getWeatherInfoList(
            @Path("path") String path,
            @Query("id") String id,
            @Query("APPID") String token);
}
