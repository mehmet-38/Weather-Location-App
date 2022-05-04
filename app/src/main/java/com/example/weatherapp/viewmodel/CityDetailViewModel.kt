package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.retrofit.RetroInstance
import com.example.weatherapp.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CityDetailViewModel:ViewModel (){
    
        lateinit var recyclerListData:MutableLiveData<WeatherModel>

        init {
                recyclerListData = MutableLiveData()
            }

    fun getWeatherObservers():MutableLiveData<WeatherModel>{
        return recyclerListData
    }


    fun getWeatherList(woeid:Int){
        val retroInstance =RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        val call = retroInstance.getWeatherList(woeid)
        call.enqueue(object :Callback<WeatherModel>{
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                recyclerListData.postValue(response.body())
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                recyclerListData.postValue(null)
            }


        })
    }
}