package com.example.weatherapp.retrofit


import com.example.weatherapp.data.LocationModel
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.data.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

import retrofit2.http.Query

interface RetroServiceInterface {

    // get location list  with api
    @GET("api/location/search")
    fun getLocationList(@Query("lattlong")lattlong:String):Call<List<LocationModel>>

    // https://www.metaweather.com/api/location/44418/
    @GET("api/location/{woeid}")
    fun getWeatherList(@Path("woeid")woeid:Int):Call<WeatherModel>

}