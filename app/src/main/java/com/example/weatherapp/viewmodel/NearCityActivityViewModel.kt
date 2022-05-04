package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.LocationModel
import com.example.weatherapp.retrofit.RetroInstance
import com.example.weatherapp.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NearCityActivityViewModel:ViewModel() {
    lateinit var liveDataList: MutableLiveData<List<LocationModel>>

    init{
        liveDataList= MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<LocationModel>> {
        return liveDataList
    }

    //
    fun makeApiCall(lattlong:String){
        var retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getLocationList(lattlong)

        call.enqueue(object : Callback<List<LocationModel>> {
            override fun onResponse(
                call: Call<List<LocationModel>>,
                response: Response<List<LocationModel>>
            ) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<LocationModel>>, t: Throwable) {
                liveDataList.postValue(null)
            }

        }


        )
    }

}