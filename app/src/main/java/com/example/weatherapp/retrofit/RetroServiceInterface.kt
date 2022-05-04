package com.example.weatherapp.retrofit


import com.example.weatherapp.data.LocationModel
import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Query

interface RetroServiceInterface {

    // get location list  with api
    @GET("api/location/search")
    fun getLocationList(@Query("lattlong")lattlong:String):Call<List<LocationModel>>




}