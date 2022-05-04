package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.data.WeatherModel
import kotlinx.android.synthetic.main.weather_list_row.view.*

class WeatherListAdapter:RecyclerView.Adapter<WeatherListAdapter.MyViewHolder>() {
    var weatherList = mutableListOf<WeatherData>()



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherListAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.weather_list_row,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: WeatherListAdapter.MyViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int {
    return weatherList.size
    }

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val stateName = view.weather_state_name
        val tempName= view.weather_temp_name
        val windName = view.weather_wind

        fun bind(data:WeatherData){
            stateName.text=data.weather_state_name
            tempName.text=data.the_temp.toString()
            windName.text=data.wind_speed.toString()
        }
    }
}