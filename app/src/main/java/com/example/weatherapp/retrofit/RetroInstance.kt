package com.example.weatherapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    //  Base url  :  https://www.metaweather.com/api/location/search/?lattlong=41.19,28.73
    // get url :  ?lattlong=41.19,28.73

    companion object {
        val BASE_URL ="https://www.metaweather.com/"

        fun getRetroInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}