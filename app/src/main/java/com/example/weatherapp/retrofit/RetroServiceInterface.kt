package com.example.weatherapp.retrofit

import android.widget.TextView
import com.example.weatherapp.data.LocationModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("api/location/search")
    fun getLocationList(@Query("lattlong")lattlong:String):Call<List<LocationModel>>


}