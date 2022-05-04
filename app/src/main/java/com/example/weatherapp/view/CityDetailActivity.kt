package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapter.WeatherListAdapter
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.viewmodel.CityDetailViewModel
import kotlinx.android.synthetic.main.activity_city_detail.*

class CityDetailActivity : AppCompatActivity() {

    private lateinit var  recyclerAdapter : WeatherListAdapter
    lateinit var viewModel : CityDetailViewModel
    lateinit var  getDetailButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail)

        getDetailButton = findViewById(R.id.getCityDetail)
        getDetailButton.setOnClickListener {
            initRecyclerView()
            initViewModel()
        }


    }

    private fun initRecyclerView(){
        weatherListRecyclerView.apply {
            layoutManager =LinearLayoutManager(this@CityDetailActivity)

            recyclerAdapter= WeatherListAdapter()
            adapter = recyclerAdapter

        }
        Toast.makeText(this,"recycler view oldu",Toast.LENGTH_SHORT).show()
    }

    fun initViewModel(){
        viewModel= ViewModelProvider(this).get(CityDetailViewModel::class.java)
        viewModel.getWeatherObservers().observe(this, Observer<WeatherModel> {
            if (it==null){
                Toast.makeText(this,"error getting list",Toast.LENGTH_SHORT).show()
            }
            else{

                recyclerAdapter.weatherList = it.consolidated_weather.toMutableList()
                recyclerAdapter.notifyDataSetChanged()
            }

        })

        viewModel.getWeatherList(44418)
    }


}