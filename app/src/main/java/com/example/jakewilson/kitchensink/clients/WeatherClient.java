package com.example.jakewilson.kitchensink.clients;

import com.example.jakewilson.kitchensink.models.WeatherResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by jakewilson on 8/18/14.
 */
public class WeatherClient {

    public static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=Salt%20Lake%20City,usa";

    public interface WeatherInterface {
        @GET("/")
        void currentWeather(Callback<WeatherResponse> cb);
    }
}
